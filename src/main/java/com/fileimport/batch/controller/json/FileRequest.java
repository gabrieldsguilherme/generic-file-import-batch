package com.fileimport.batch.controller.json;

import com.fileimport.batch.enumeration.FileType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRequest {
	
	private String key;
	
	private String bucket;
	
	private FileType type;

}
