package com.logreapermq.LogReaperMQ.Security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.logreapermq.LogReaperMQ.QueueSystem.QueueUniqueIdentificatorSystem;
import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;

@EnableScheduling
public class QueuesSecuritySystem {
    @Autowired
    private TopicHandler topicManager;

    @Bean
    public QueuesSecuritySystem queueLengthController() {
        return new QueuesSecuritySystem();
    }
    
    @Scheduled(fixedRate = 3000)
    public SystemErrorsBinder checkQueuesDimension() {
        Map<QueueUniqueIdentificatorSystem, QueuesManager> topicHandler = topicManager.getTopicHandler();
        return null;
    }
}
