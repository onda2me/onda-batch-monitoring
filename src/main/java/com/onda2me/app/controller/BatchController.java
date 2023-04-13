package com.onda2me.app.controller;

import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onda2me.app.batch.config.SimpleJobConfiguration;
import com.onda2me.app.util.DateUtil;
import com.onda2me.app.util.IConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {

    private final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    SimpleJobConfiguration simpleJobConfig;    
//    
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job job;
    
    @Autowired
    ApplicationContext context;
 
    @GetMapping("/simpleJob")
    public ExitStatus simple(Locale locale) throws Exception {

        String launchDate = DateUtil.getDate("yyyyMMdd");
        String launchTime = DateUtil.getDate("HHmmss");
        
        JobParameters jobParams = new JobParametersBuilder()
                .addString("launchDate", launchDate)
                .addString("launchTime", launchTime)
                .addString("channel", IConstants.BATCH_CHANNEL_WEB)
                .toJobParameters();
        
        logger.debug("================================");
        logger.debug("start");
        
        JobExecution jobExecution = 
                jobLauncher.run(simpleJobConfig.executeJob(), jobParams);
                
        logger.debug("end");        
        logger.debug("================================");
        
        return jobExecution.getExitStatus();
    }
    
    /**
     * JobRepository의 Job 실행
     * @return
     * @throws Exception
     */
    @RequestMapping("/launchJob")
    public ExitStatus launchJob() throws Exception {
 
    	String launchDate = DateUtil.getDate("yyyyMMdd");
    	String launchTime = DateUtil.getDate("HHmmss");
		
		JobParameters jobParams = new JobParametersBuilder()
                .addString("launchDate", launchDate)
                .addString("launchTime", launchTime)
                .addString("channel", IConstants.BATCH_CHANNEL_WEB)
				.toJobParameters();
 
        return jobLauncher.run(job, jobParams).getExitStatus();
    }
    
    /**
     * Job name에 해당하는 Job 실행
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping("/runJob")
    public ExitStatus runJob(@RequestParam HashMap<String, String> param) throws Exception {
    	
        Job runJob = null;
        JobExecution jobExecution = null;
        ExitStatus exitStatus = null;
        
    	String jobName = param.get("jobName");
    	String launchDate = DateUtil.getDate("yyyyMMdd");
    	String launchTime = DateUtil.getDate("HHmmss");
    	
        JobParameters jobParams = new JobParametersBuilder()
                .addString("jobName", jobName)
                .addString("launchDate", launchDate)
                .addString("launchTime", launchTime)
                .addString("channel", IConstants.BATCH_CHANNEL_WEB)
                .toJobParameters();
        
        logger.debug("================================");
        logger.debug(">>>>> runJob.param : "+ param);
        
        try {
            runJob = context.getBean(jobName, Job.class);
            jobExecution = jobLauncher.run(runJob, jobParams);
            exitStatus = jobExecution.getExitStatus();
            
        } catch (NoSuchBeanDefinitionException ex) {
            ex.printStackTrace();
            
            exitStatus = new ExitStatus("500", ex.getMessage());
        }
                    
        logger.debug(">>>>> runJob.exitStatus : "+ exitStatus);
        logger.debug("================================");
     
        return exitStatus;
    }
}