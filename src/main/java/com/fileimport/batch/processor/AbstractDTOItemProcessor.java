package com.fileimport.batch.processor;

import com.fileimport.batch.dto.DTO;

public abstract class AbstractDTOItemProcessor implements DTOItemProcessor {
	
	protected DTO dto;
	
	public AbstractDTOItemProcessor(DTO dto) {
		this.dto = dto;
	}
	
	public DTO getDto() {
		return dto;
	}

}
