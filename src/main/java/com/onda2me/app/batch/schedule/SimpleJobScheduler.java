package com.onda2me.app.batch.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.onda2me.app.batch.config.SimpleJobConfiguration;
import com.onda2me.app.util.DateUtil;
import com.onda2me.app.util.IConstants;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class SimpleJobScheduler {
    
    private final Logger logger = LoggerFactory.getLogger(SimpleJobScheduler.class);
    
    private final JobLauncher jobLauncher;
    private final SimpleJobConfiguration jobConfig;
    
//    @Scheduled(fixedRate = 60000) // 단위 ms 
//    @Scheduled(cron = "30 * * * * ?") // 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
    public String executeScheduler() {
        
        logger.debug("================================");
        logger.debug("start");
        
        try {
        	
            String launchDate = DateUtil.getDate("yyyyMMdd");
            String launchTime = DateUtil.getDate("HHmmss");
            
            JobParameters jobParams = new JobParametersBuilder()
                    .addString("launchDate", launchDate)
                    .addString("launchTime", launchTime)
                    .addString("channel", IConstants.BATCH_CHANNEL_SCHEDULER)
                    .toJobParameters();
        
            JobExecution jobExecution = 
                    jobLauncher.run(jobConfig.executeJob(), jobParams);
            
            while(jobExecution.isRunning()) {
                Thread.sleep(10000);
                logger.info("job is running....");
            }
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        logger.debug("end");
        logger.debug("================================\n\n");
        
        return "success";
     
    }
}
