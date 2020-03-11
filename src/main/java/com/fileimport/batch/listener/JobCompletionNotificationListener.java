package com.fileimport.batch.listener;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
    	log.info("Finished - Executed in " + (System.currentTimeMillis() - jobExecution.getJobParameters().getLong("initial")));
    	
        jobExecution.getStepExecutions().forEach(step -> {
    		log.info("Step " + step.getStepName());
    		log.info("Readed: " + step.getReadCount());
    		log.info("Writed: " + step.getWriteCount());
    		log.info("Process Skipped: " + step.getProcessSkipCount());
    		log.info("Summary: " + step.getSummary());
    	});
        
        File f = new File("file.csv");
        f.delete();
    }
}
