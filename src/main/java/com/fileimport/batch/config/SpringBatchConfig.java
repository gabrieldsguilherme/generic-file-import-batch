package com.fileimport.batch.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.fileimport.batch.dto.DTO;
import com.fileimport.batch.enumeration.FileType;
import com.fileimport.batch.listener.JobCompletionNotificationListener;
import com.fileimport.batch.processor.CustomItemProcessor;
import com.fileimport.batch.writer.FileInfoItemWriter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {
	
	private final AmazonS3 s3;
	private final AmazonDynamoDB amazonDynamoDB;
	private final StepBuilderFactory steps;
	
	@Bean
	public TaskExecutor taskExecutor(){
	    SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
	    asyncTaskExecutor.setConcurrencyLimit(30);
	    return asyncTaskExecutor;
	}

	@Bean
	public Step step() throws IOException {
	    return steps.get("Extract -> Transform -> Aggregate -> Load").<DTO, DTO> chunk(1000000)
	            .reader(itemReader(null, null, null))
	            .processor(itemProcessor())
	            .writer(itemWriter())
	            .taskExecutor(taskExecutor()).build();
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<? extends DTO> itemReader(@Value("#{jobParameters[key]}") String key,
			@Value("#{jobParameters[bucket]}") String bucket, 
			@Value("#{jobParameters[type]}") String type) throws IOException {
		FlatFileItemReader<? extends DTO> reader = FileType.valueOf(type).getReader();
		reader.setResource(new ByteArrayResource(getBytes(bucket, key), "s3 bytes"));
		return reader;
	}
	
	@Bean
	public ItemProcessor<DTO, DTO> itemProcessor() {
		return new CustomItemProcessor();
	}
	
	@Bean
	public FileInfoItemWriter itemWriter() {
		return new FileInfoItemWriter(amazonDynamoDB);
	}
	
	@Bean
	public JobCompletionNotificationListener listener() {
		return new JobCompletionNotificationListener();
	}
	
	private byte[] getBytes(String bucket, String key) throws IOException {
        S3Object object = s3.getObject(new GetObjectRequest(bucket, key));
        try (InputStream is = object.getObjectContent()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(is, out);
            return out.toByteArray();
        }
    }
	
}