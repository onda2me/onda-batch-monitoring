package com.onda2me.app.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration {

    private final Logger logger = LoggerFactory.getLogger(SimpleJobConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;    
    
    private final String JOB_NAME = "simpleJob";
    
    public String getJobName() {
        return this.JOB_NAME;
    }
    
    @Bean(name=JOB_NAME)
    @Primary
    public Job executeJob() {	// Job
        return jobBuilderFactory.get(JOB_NAME)
                    .start(simpleStep1())
                    .build();
    }

    @Bean(name = JOB_NAME+"Step1")
    public Step simpleStep1() {	// Step -> tasklet -> 로그출력
        
        return stepBuilderFactory.get(JOB_NAME+"Step1")
	                 .tasklet((contribution, chunkContext) -> {
	                     
	                     logger.debug(">>>>> This is "+JOB_NAME+".Step1");
	                     logger.debug(">>>>> Job Params : " + chunkContext.getStepContext().getJobParameters());
	                     
	                     return RepeatStatus.FINISHED;
	                 })
	                 .build();
    }
}
