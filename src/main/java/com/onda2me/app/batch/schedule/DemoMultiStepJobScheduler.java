package com.onda2me.app.batch.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.onda2me.app.batch.config.MultiStepJobConfiguration;
import com.onda2me.app.util.DateUtil;
import com.onda2me.app.util.IConstants;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class DemoMultiStepJobScheduler {
    
    private final Logger logger = LoggerFactory.getLogger(DemoMultiStepJobScheduler.class);
    
    private final JobLauncher jobLauncher;
    private final MultiStepJobConfiguration jobConfig;

    @Scheduled(fixedRate = 1000*30) // 1000ms(=1초)*n초
    //@Scheduled(cron = "10 * * * * ?") // 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
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
        
            jobLauncher.run(jobConfig.executeJob(), jobParams);            

        } catch (JobInstanceAlreadyCompleteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        logger.debug("end");
        logger.debug("================================\n\n");
        
        return "success";
    }
}
