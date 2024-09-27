package com.logreapermq.LogReaperMQ.Security;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;



@Configuration
@EnableScheduling
public class QueuesSecuritySystem {
    @Autowired
    private TopicHandler topicManager;
    @Autowired
    private AsyncQueuesDimControllers asyncQueueController;

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void checkQueuesDimension() {
        System.out.println("Security System on...");
        Map<String, QueuesManager> topicHandler = topicManager.getTopicHandler();
        List<String> topics = topicHandler.entrySet().stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        for (var topicToCheck : topics) {
            asyncQueueController.checkQueueDimension(topicToCheck, topicHandler);
        }
    }
}