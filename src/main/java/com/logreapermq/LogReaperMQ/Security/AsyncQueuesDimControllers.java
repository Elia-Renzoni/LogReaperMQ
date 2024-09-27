package com.logreapermq.LogReaperMQ.Security;

import java.util.Map;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;

@EnableAsync
public class AsyncQueuesDimControllers {
    private final static Long MAX_DIM = 5_000_000L; 

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor tPool = new ThreadPoolTaskExecutor();
        tPool.setMaxPoolSize(150);
        tPool.setQueueCapacity(150);
        tPool.setCorePoolSize(150);
        tPool.initialize();

        return tPool;
    }
    
    @Async("threadPoolTaskExecutor")
    public void checkQueueDimension(final String topic, Map<String, QueuesManager> queueHandler) {
        QueuesManager manager = (QueuesManager) queueHandler.entrySet().stream()
            .filter(n -> n.getKey().equals(topic))
            .map(Map.Entry::getValue);
        
        manager.getTopicQueues().stream()
            .filter(q -> q.getQueueMemoryDimension() >= AsyncQueuesDimControllers.MAX_DIM)
            .forEach(q -> q.setDirtyBitToFalse());
    }
}
