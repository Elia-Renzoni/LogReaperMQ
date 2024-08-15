package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

@Service
@Scope("singleton")
public class TopicHandler {
    private Map<String, QueuesManager> mainHandler;

    public TopicHandler() {
        this.mainHandler = new HashMap<>();
    }

    public synchronized SystemErrorsBinder addNewTopic(final String topicName) {
        Boolean isTopicAlreadyPresent = this.checkTopicName(topicName);
        
        if (isTopicAlreadyPresent) {
            return SystemErrorsBinder.TOPIC_ALREADY_EXIST;
        }

        this.mainHandler.put(topicName, new QueuesManager());
        return SystemErrorsBinder.OK_STATUS;
    }
    
    public synchronized SystemErrorsBinder deleteTopic(final String topicName) {
        Boolean isTopicPresent = this.checkTopicName(topicName);
        
        if (isTopicPresent) {
            this.mainHandler.remove(topicName);
        } else {
            return SystemErrorsBinder.UNKNOWN_TOPIC;
        }
        return SystemErrorsBinder.OK_STATUS;
    }

    public synchronized SystemErrorsBinder addQueue(final String topicName, final String queueName) {
        SystemErrorsBinder opResult;
        // search for main topic
        Optional<QueuesManager> manager = this.checkTopicAndGetManager(topicName);

        if (manager.isPresent()) {
            opResult = manager.get().addQueue(queueName);
        } else {
            return SystemErrorsBinder.UNKNOWN_TOPIC;
        }
        return opResult;
    }

    public synchronized SystemErrorsBinder deleteQueue(final String topicName, final String queueName) {
        SystemErrorsBinder opResult;

        // search for the main topic
        Optional<QueuesManager> manager = this.checkTopicAndGetManager(topicName);

        if (manager.isPresent()) {
            opResult = manager.get().deleteQueue(queueName);
        } else {
            return SystemErrorsBinder.UNKNOWN_TOPIC;
        }
        return opResult;
    }

    public synchronized Map<String, QueuesManager> getTopicHandler() {
        return this.mainHandler;
    }

    private Optional<QueuesManager> checkTopicAndGetManager(final String topicNameToSearch) {
        return this.mainHandler.entrySet().stream()
                .filter(kv -> kv.getKey().equals(topicNameToSearch))
                .map(Map.Entry::getValue)
                .findFirst();
    }
    
    private Boolean checkTopicName(final String topicNametoSearch) {
        return this.mainHandler.entrySet().stream()
            .map(Map.Entry::getKey)
            .filter(k -> k.equals(topicNametoSearch))
            .anyMatch(null);
    }
}
