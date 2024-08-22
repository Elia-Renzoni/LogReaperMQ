package com.logreapermq.LogReaperMQ.Security;

import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;

@EnableAsync
public class AsyncQueuesDimControllers {
    private final static Long MAX_DIM = 5_000_000L; 
    
    @Async
    public void checkQueueDimension(final String topic, Map<String, QueuesManager> queueHandler) {
        QueuesManager manager = (QueuesManager) queueHandler.entrySet().stream()
            .filter(n -> n.getKey().equals(topic))
            .map(Map.Entry::getValue);
        
        manager.getTopicQueues().stream()
            .filter(q -> q.getQueueMemoryDimension() >= AsyncQueuesDimControllers.MAX_DIM)
            .forEach(q -> q.setDirtyBitToFalse());
    }
}