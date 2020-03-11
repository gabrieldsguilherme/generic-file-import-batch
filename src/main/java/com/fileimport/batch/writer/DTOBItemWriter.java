package com.fileimport.batch.writer;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.fileimport.batch.dto.DTO;
import com.fileimport.batch.dto.DTOB;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DTOBItemWriter extends AbstractDTOItemWriter {
	
	public DTOBItemWriter(DTO dto) {
		super(dto);
	}

	@Override
	public void write(DynamoDB dynamoDB) {
		log.info("Writing B...");
		DTOB dtob = (DTOB) dto;
		log.info("Writed: " + dtob);
		dynamoDB.getTable("Email");
	}

}
