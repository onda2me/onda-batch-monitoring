package com.onda2me.app.batch.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * https://www.javainuse.com/spring/bootTask
 * 
 * @author congiya
 *
 */
@Component
public class DemoTasksScheduler {
	
	private final Logger logger = LoggerFactory.getLogger(DemoTasksScheduler.class);
	
	private static final SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//	@Scheduled(fixedRate = 10000) // 10000ms -> 10s 
	public void performTask() {

		logger.debug("\t{} ", dateFormat.format(new Date()));
	}

//	@Scheduled(initialDelay = 5000, fixedRate = 10000)
	public void performTaskDelay() {

	    logger.debug("\t{} ", dateFormat.format(new Date()));
	}

//	@Scheduled(cron = "2/10 * * * * *")
	public void performTaskCron() {

	    logger.debug("\t{} ", dateFormat.format(new Date()));
	}
}
