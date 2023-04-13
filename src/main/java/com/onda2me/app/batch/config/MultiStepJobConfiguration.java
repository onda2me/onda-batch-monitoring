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

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MultiStepJobConfiguration {
    
    private final Logger logger = LoggerFactory.getLogger(MultiStepJobConfiguration.class);
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    /*
     * --job.name=multiStepJob
     */
    private final String JOB_NAME = "multiStepJob";

    public String getJobName() {
        return JOB_NAME;
    }
    
    @Bean(JOB_NAME)    
    public Job executeJob() {    
        return jobBuilderFactory.get(JOB_NAME)
                    .start(step1())
                    .next(step2())
                    .next(step3())                                
                    .build();
    }

    @Bean(name = JOB_NAME+"Step1")
    public Step step1() {    
        return stepBuilderFactory.get(JOB_NAME+"Step1")
                 .tasklet((contribution, chunkContext) -> {
                     
                     logger.debug("----------------------");
                     logger.debug(">>>>> This is "+JOB_NAME+".Step1");
                     logger.debug(">>>>> Job Params : " + chunkContext.getStepContext().getJobParameters());
                     logger.debug("----------------------");
                     
                     return RepeatStatus.FINISHED;
                 })
                 .build();
    }
    
    @Bean(name = JOB_NAME+"Step2")
    public Step step2() {    
        return stepBuilderFactory.get(JOB_NAME+"Step2")
                 .tasklet((contribution, chunkContext) -> {
                     
                     logger.debug("----------------------");
                     logger.debug(">>>>> This is "+JOB_NAME+".Step2");
                     logger.debug("----------------------");
                     
//                     throw new RuntimeException("step2 failed");
                     
                     return RepeatStatus.FINISHED;
                 })
                 .build();
    }  
    
    @Bean(name = JOB_NAME+"Step3")
    public Step step3() {    
        return stepBuilderFactory.get(JOB_NAME+"Step3")
                 .tasklet((contribution, chunkContext) -> {
                     
                     logger.debug("----------------------");
                     logger.debug(">>>>> This is "+JOB_NAME+".Step3");
                     logger.debug("----------------------");
                     
                     return RepeatStatus.FINISHED;
                 })
                 .build();
    }
}
