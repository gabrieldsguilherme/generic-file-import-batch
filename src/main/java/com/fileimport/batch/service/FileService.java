package com.fileimport.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fileimport.batch.controller.json.FileRequest;
import com.fileimport.batch.listener.JobCompletionNotificationListener;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {

	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Step step;
	
	@Autowired
	private JobCompletionNotificationListener listener;
	
	@Async
	public void importFile(FileRequest file) throws Exception {
        System.out.println("Starting the batch job");
        try {
        	Job job = jobs.get("FxMarket Volume ETL Job")
        			.incrementer(new RunIdIncrementer())
        			.listener(listener)
    	            .flow(step)
    	            .end()
    	            .build();
        	
        	JobParameters jobParameters = new JobParametersBuilder()
        			.addString("key", file.getKey())
        			.addString("bucket", file.getBucket())
        			.addLong("initial", System.currentTimeMillis())
        			.addString("type", file.getType().name())
        			.toJobParameters();
        	
        	final JobExecution execution = jobLauncher.run(job, jobParameters);
            log.info("Job succeeded " + execution.getEndTime());
        } catch (final Exception e) {
            log.error("Job failed!");
        }
	}
	
}
