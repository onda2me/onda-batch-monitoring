package com.onda2me.app.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.onda2me.app.batch.task.CommonTasklet;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MultiTaskletJobConfiguration {

	private final Logger logger = LoggerFactory.getLogger(MultiTaskletJobConfiguration.class);
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
    private final String JOB_NAME = "multiTaskletJob";

    public String getJobName() {
        return JOB_NAME;
    }
    
    @Bean(JOB_NAME)
    public Job executeJob() {
    	
        return jobBuilderFactory.get(JOB_NAME)
                .start(useSimpleTaskletStep())
                .next(useInnerTaskletStep(getInnerTasklet()))
                .next(useCommonTaskletStep())            
                .build();
    }

    @Bean(name = JOB_NAME+"Step1")
    @JobScope
    //public Step helloStep1(@Value("#{jobParameters[requestTime]}") String requestTime) {
    public Step useSimpleTaskletStep() {
    	
        return stepBuilderFactory.get(JOB_NAME+"Step1")
                .tasklet((contribution, chunkContext) -> {

                	logger.debug("--------------------------------------------");
                	logger.debug(">>>>> This is "+JOB_NAME+".Step1");
                    logger.debug(">>>>> Job Params : " + chunkContext.getStepContext().getJobParameters());
                	logger.debug("--------------------------------------------"); 
                	
//                    if("20220902".startsWith(requestTime)) {
//                        throw new IllegalArgumentException("잘못된 값입니다.");
//                    }
//
//                	logger.debug("--------------------------------------------");
//                    logger.debug("HelloJobConfiguration."+JOB_NAME+" helloStep1 requestTime : " + requestTime);
//                	logger.debug("--------------------------------------------"); 
                	
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
      
    @Bean(JOB_NAME+"Step2")
    @JobScope
    public Step useInnerTaskletStep(Tasklet tasklet) {
    	
        return stepBuilderFactory.get(JOB_NAME+"Step2")
                .tasklet(getInnerTasklet())
                .build();
    }

    @Bean(JOB_NAME+"Step3")
    @JobScope
    public Step useCommonTaskletStep() {
    	
        return stepBuilderFactory.get(JOB_NAME+"Step3")
                .tasklet(new CommonTasklet())
                .build();
    }    
    
    @Bean
    @StepScope
    public Tasklet getInnerTasklet() {
    	
        return (contribution, chunkContext) -> {
        	logger.debug("--------------------------------------------");
        	logger.debug(">>>>> This is "+JOB_NAME+"."+contribution.getStepExecution().getStepName());
            logger.debug(">>>>> inner tasklet");
        	logger.debug("--------------------------------------------"); 
            return RepeatStatus.FINISHED;
        };
    }
}