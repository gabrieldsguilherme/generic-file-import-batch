package com.fileimport.batch.writer;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.fileimport.batch.dto.DTO;
import com.fileimport.batch.dto.DTOA;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DTOAItemWriter extends AbstractDTOItemWriter {
	
	public DTOAItemWriter(DTO dto) {
		super(dto);
	}

	@Override
	public void write(DynamoDB dynamoDB) {
		log.info("Writing A...");
		DTOA dtoa = (DTOA) dto;
		log.info("Writed: " + dtoa);
		dynamoDB.getTable("Email");
	}

}
