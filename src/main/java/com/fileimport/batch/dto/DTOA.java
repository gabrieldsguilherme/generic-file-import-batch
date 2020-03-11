package com.fileimport.batch.dto;

import com.fileimport.batch.processor.DTOAItemProcessor;
import com.fileimport.batch.processor.DTOItemProcessor;
import com.fileimport.batch.writer.DTOAItemWriter;
import com.fileimport.batch.writer.DTOItemWriter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DTOA implements DTO {
	
	private String field1;
	
	private String field2;
	
	@Override
	public DTOItemProcessor getProcessor() {
		return new DTOAItemProcessor(this);
	}

	@Override
	public DTOItemWriter getWriter() {
		return new DTOAItemWriter(this);
	}

}
