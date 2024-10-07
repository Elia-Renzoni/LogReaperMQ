package com.logreapermq.LogReaperMQ.Security;

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
public class AsyncQueuesDimControllers {
    private final static Long MAX_DIM = 5_000_000L; 

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor tPool = new ThreadPoolTaskExecutor();
        tPool.setMaxPoolSize(101);
        tPool.setQueueCapacity(101);
        tPool.setCorePoolSize(101);
        tPool.initialize();

        return tPool;
    }
    
    @Async("threadPoolTaskExecutor")
    public void checkQueueDimension(final List<QueuesManager> queueHandler) {
        for (var manager : queueHandler) {
            manager.getTopicQueues().stream()
                .filter(q -> q.getQueueMemoryDimension() >= AsyncQueuesDimControllers.MAX_DIM)
                .forEach(q -> q.setDirtyBitToFalse());
        }
    }
}
