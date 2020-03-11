package com.fileimport.batch.processor;

import com.fileimport.batch.dto.DTO;
import com.fileimport.batch.dto.DTOB;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DTOBItemProcessor extends AbstractDTOItemProcessor {

	public DTOBItemProcessor(DTO dto) {
		super(dto);
	}

	@Override
	public DTO process() {
		log.info("Processing B...");
		DTOB dtob = (DTOB) dto;
		log.info("Processed: " + dtob);
		return dtob;
	}

}
