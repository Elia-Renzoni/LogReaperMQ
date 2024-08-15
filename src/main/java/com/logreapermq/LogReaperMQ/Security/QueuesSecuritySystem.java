package com.logreapermq.LogReaperMQ.Security;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Scheduled(fixedRate = 3000)
    public SystemErrorsBinder checkQueuesDimension() {
        Map<String, QueuesManager> topicHandler = topicManager.getTopicHandler();
        List<String> topics = topicHandler.entrySet().stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        return null;
    }


}
