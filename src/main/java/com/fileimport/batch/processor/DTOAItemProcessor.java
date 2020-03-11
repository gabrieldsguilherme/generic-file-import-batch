package com.fileimport.batch.processor;

import com.fileimport.batch.dto.DTO;
import com.fileimport.batch.dto.DTOA;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DTOAItemProcessor extends AbstractDTOItemProcessor {

	public DTOAItemProcessor(DTO dto) {
		super(dto);
	}

	@Override
	public DTO process() {
		log.info("Processing A...");
		DTOA dtoa = (DTOA) dto;
		log.info("Processed: " + dtoa);
		return dtoa;
	}

}
