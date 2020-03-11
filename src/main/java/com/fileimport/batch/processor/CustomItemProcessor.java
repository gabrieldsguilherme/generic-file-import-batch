
package com.fileimport.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.fileimport.batch.dto.DTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomItemProcessor implements ItemProcessor<DTO, DTO> {
	
    public DTO process(DTO dto) {
    	try {
    		dto.getProcessor().process();
		} catch (Exception e) {
			log.error("ERROR: " + e.getMessage());
			return null;
		}
    	
    	return dto;
    }
    
}