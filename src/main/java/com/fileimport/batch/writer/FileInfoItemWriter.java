
package com.fileimport.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.fileimport.batch.dto.DTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FileInfoItemWriter implements ItemWriter<DTO> {
	
	private final AmazonDynamoDB amazonDynamoDB;
	
	@Override
	public void write(List<? extends DTO> items) throws Exception {
		items.forEach(dto -> {
			try {
	    		dto.getWriter().write(new DynamoDB(amazonDynamoDB));
			} catch (Exception e) {
				log.error("ERROR: " + e.getMessage());
			}
		});
	}
	
}