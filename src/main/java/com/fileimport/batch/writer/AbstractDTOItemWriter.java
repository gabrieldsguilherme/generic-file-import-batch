package com.fileimport.batch.writer;

import com.fileimport.batch.dto.DTO;

public abstract class AbstractDTOItemWriter implements DTOItemWriter {
	
	protected DTO dto;
	
	public AbstractDTOItemWriter(DTO dto) {
		this.dto = dto;
	}
	
	public DTO getDto() {
		return dto;
	}

}
