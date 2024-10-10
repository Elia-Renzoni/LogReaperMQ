package com.logreapermq.LogReaperMQ.Storage;

import java.util.concurrent.Executor;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;

@Configuration
@EnableAsync
public class AsyncStorage {

    @Bean(name = "threadPoolTaskExecutorStorage")
    public Executor storageTaskExecutor() {
        ThreadPoolTaskExecutor tPool = new ThreadPoolTaskExecutor();
        tPool.setMaxPoolSize(101);
        tPool.setQueueCapacity(101);
        tPool.setCorePoolSize(101);
        return tPool;
    }

    @Async("threadPoolTaskExecutorStorage")
    public void storeAndDelete(final List<QueuesManager> managers) {
        for (var toDelete : managers) {
            // store data
            for (var queues : toDelete.getTopicQueues()) {
                queues.deleteItems();
            }
        }
    }
}
