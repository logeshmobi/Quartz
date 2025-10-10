package io.gomobi.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Clock;

@Configuration
public class ApplicationConfig {

    @Bean
    public ThreadPoolTaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(2);
        threadPoolTaskScheduler.setClock(Clock.systemUTC());
        threadPoolTaskScheduler.setVirtualThreads(true);
        threadPoolTaskScheduler.setThreadNamePrefix("Scheduler-");
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }
}
