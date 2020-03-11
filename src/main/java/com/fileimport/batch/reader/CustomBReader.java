package com.fileimport.batch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;

import com.fileimport.batch.dto.DTOB;

public class CustomBReader<T> extends FlatFileItemReader<DTOB> {
	
	public static Logger logger = LoggerFactory.getLogger(CustomBReader.class);
	
	@Override
	protected DTOB doRead() throws Exception {
		try {
			return super.doRead();
		} catch (FlatFileParseException e) {
			logger.error("Erro ao fazer parse da linha {}. Layout inválido: {}", e.getLineNumber(), e.getInput());
			return null;
		}
	}
}