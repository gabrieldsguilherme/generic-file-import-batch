package com.fileimport.batch.enumeration;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import com.fileimport.batch.dto.DTO;
import com.fileimport.batch.dto.DTOA;
import com.fileimport.batch.dto.DTOB;
import com.fileimport.batch.reader.CustomAReader;
import com.fileimport.batch.reader.CustomBReader;

public enum FileType {
	
	A {
		@Override
		public FlatFileItemReader<DTOA> getReader() {
			DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");
			lineTokenizer.setNames(new String [] { "field1", "field2" });

			BeanWrapperFieldSetMapper<DTOA> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
			fieldSetMapper.setTargetType(DTOA.class);

			DefaultLineMapper<DTOA> lineMapper = new DefaultLineMapper<>();
			lineMapper.setLineTokenizer(lineTokenizer);
			lineMapper.setFieldSetMapper(fieldSetMapper);

			CustomAReader<DTOA> reader = new CustomAReader<>();
			reader.setLinesToSkip(1);
			reader.setLineMapper(lineMapper);

			return reader;
		}
	},
	B {
		@Override
		public FlatFileItemReader<DTOB> getReader() {
			DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");
			lineTokenizer.setNames(new String [] { "fieldA", "fieldB", "fieldC" });

			BeanWrapperFieldSetMapper<DTOB> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
			fieldSetMapper.setTargetType(DTOB.class);

			DefaultLineMapper<DTOB> lineMapper = new DefaultLineMapper<>();
			lineMapper.setLineTokenizer(lineTokenizer);
			lineMapper.setFieldSetMapper(fieldSetMapper);

			CustomBReader<DTOB> reader = new CustomBReader<>();
			reader.setLinesToSkip(1);
			reader.setLineMapper(lineMapper);

			return reader;
		}
	};

	public abstract FlatFileItemReader<? extends DTO> getReader();

}
