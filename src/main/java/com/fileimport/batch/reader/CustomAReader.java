package com.fileimport.batch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;

import com.fileimport.batch.dto.DTOA;

public class CustomAReader<T> extends FlatFileItemReader<DTOA> {
	
	public static Logger logger = LoggerFactory.getLogger(CustomAReader.class);
	
	@Override
	protected DTOA doRead() throws Exception {
		try {
			DTOA dto = super.doRead();
			return dto;
		} catch (FlatFileParseException e) {
			logger.error("Erro ao fazer parse da linha {}. Layout inválido: {}", e.getLineNumber(), e.getInput());
			return null;
		}
	}
}