package com.fileimport.batch.dto;

import com.fileimport.batch.processor.DTOItemProcessor;
import com.fileimport.batch.writer.DTOItemWriter;

public interface DTO {

	DTOItemProcessor getProcessor();
	
	DTOItemWriter getWriter();

}
