package com.onda2me.app.batch.task;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CommonTasklet implements Tasklet {

	private final Logger logger = LoggerFactory.getLogger(CommonTasklet.class);
	
    // JobParameters 사용
//    @Value("#{jobParameters[datetime]}")
//    private String datetime;
    
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    	
    	StepContext context = chunkContext.getStepContext();
    	
    	logger.debug("--------------------------------------------");
    	logger.debug(">>>>> This is {}.{}",  context.getJobName(), context.getStepName());
    	logger.debug(">>>>> jobParams : {} ", context.getJobParameters());
        logger.debug(">>>>> execute common tasklet");
    	logger.debug("--------------------------------------------"); 
        ;
        return RepeatStatus.FINISHED;
    }
}