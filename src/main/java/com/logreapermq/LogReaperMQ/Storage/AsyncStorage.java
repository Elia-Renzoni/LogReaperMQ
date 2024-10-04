package com.logreapermq.LogReaperMQ.Storage;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncStorage {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor storageTaskExecutor() {
        ThreadPoolTaskExecutor tPool = new ThreadPoolTaskExecutor();
        return tPool;
    }

    @Async("threadPoolTaskExecutor")
    public void storeAndDelete() {

    }
    
}
