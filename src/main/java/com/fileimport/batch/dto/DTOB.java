package com.fileimport.batch.dto;

import com.fileimport.batch.processor.DTOBItemProcessor;
import com.fileimport.batch.processor.DTOItemProcessor;
import com.fileimport.batch.writer.DTOBItemWriter;
import com.fileimport.batch.writer.DTOItemWriter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTOB implements DTO {
	
	private String fieldA;
	
	private String fieldB;
	
	private String fieldC;

	@Override
	public DTOItemProcessor getProcessor() {
		return new DTOBItemProcessor(this);
	}

	@Override
	public DTOItemWriter getWriter() {
		return new DTOBItemWriter(this);
	}

}
