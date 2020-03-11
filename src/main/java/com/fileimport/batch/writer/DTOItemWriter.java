package com.fileimport.batch.writer;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public interface DTOItemWriter {
	
	void write(DynamoDB dynamoDB);

}
