package com.onda2me.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class }) // Security 비활성
@ComponentScan(basePackages = "com.onda2me.app")
@MapperScan(basePackages = "com.onda2me.app.mapper")
@EnableBatchProcessing        // 배치 사용
//@EnableScheduling             // 스케줄링 사용
public class OndaBootApplication {

    /*
     * # 스프링부트 실행 실패시 참고!!
     * https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html
     * 
     * # Jar 실행 설정 
     * $ java –jar sample-application.jar –Dspring.profiles.active=local
     * VM Argument : -Dspring.profiles.active=local
     * 
     */
    public static void main(String[] args) {
        SpringApplication.run(OndaBootApplication.class, args);
    }
}
