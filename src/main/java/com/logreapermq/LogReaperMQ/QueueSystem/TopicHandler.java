package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

@Service
@Scope("singleton")
public class TopicHandler {
    private Map<String, QueuesManager> mainHandler;
    private List<String> dirtyTopics;

    private static final Integer MAX_TOPICS = 300; 

    public TopicHandler() {
        this.mainHandler = new HashMap<>();
        this.dirtyTopics = new LinkedList<>();
    }

    public synchronized SystemErrorsBinder addNewTopic(final String topicName) {
        Boolean isTopicAlreadyPresent = this.checkTopicName(topicName);
        
        if (this.mainHandler.size() >= TopicHandler.MAX_TOPICS) {
            return SystemErrorsBinder.TOO_MUTCH_ELEMENTS;
        }

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

        if (!(manager.isEmpty())) {
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

        if (!(manager.isEmpty())) {
            opResult = manager.get().deleteQueue(queueName);
        } else {
            return SystemErrorsBinder.UNKNOWN_TOPIC;
        }
        return opResult;
    }

    public synchronized Map<String, QueuesManager> getTopicHandler() {
        return this.mainHandler;
    }
    
    public synchronized SystemErrorsBinder checkTopicsForSubscribers(List<String> topics) {
        Boolean result = true;
        for (String topic : topics) {
            result = this.mainHandler.entrySet().stream()
                .map(Map.Entry::getKey)
                .anyMatch(t -> t.contains(topic));
            
            if (!(result)) {
                return SystemErrorsBinder.UNKNOWN_TOPIC; 
            }
        }
        return SystemErrorsBinder.OK_STATUS;
    }
    
    public synchronized SystemErrorsBinder checkQueuesForSubscribers(List<String> topics, List<String> queues) {
        Integer successCounter = 0; 
        
        for (String topic : topics) {
            QueuesManager entry = this.mainHandler.get(topic);
            for (String queue : queues) {
                for (var q : entry.getTopicQueues()) {
                    if (q.getQueueName().equals(queue)) {
                        ++successCounter;
                    }
                }
            }
        }

        if (!(successCounter == queues.size())) {
            return SystemErrorsBinder.UNKNOWN_QUEUE;
        }
        
        return SystemErrorsBinder.OK_STATUS;
    }
    
    public synchronized SystemErrorsBinder addLog(String topic, String queue, String log) {
        Optional<QueuesManager> manager = this.checkTopicAndGetManager(topic);
        SystemErrorsBinder opResult;
        
        if (manager.isPresent()) {
            Tuple<Boolean, QueueEnvironment> isQueuePresent = manager.get().searchQueue(queue);
            if (!(isQueuePresent.getOpResult())) {
                return SystemErrorsBinder.UNKNOWN_QUEUE;
            }
            opResult = isQueuePresent.getAssociateQueue().addItem(log);
        } else {
            return SystemErrorsBinder.UNKNOWN_TOPIC;
        }

        return opResult;
    }

    public synchronized void addDirtyTopics(final List<String> topics) {
        this.dirtyTopics.addAll(topics);
    }

    public synchronized void deleteDirtyTopics(final String topic) {
        this.dirtyTopics.remove(topic);
    }

    public synchronized List<String> getDirtyTopics() {
        return this.dirtyTopics;
    }

    private Optional<QueuesManager> checkTopicAndGetManager(final String topicNameToSearch) {
        if (this.mainHandler.containsKey(topicNameToSearch)) {
            return Optional.of(this.mainHandler.get(topicNameToSearch));
        } else {
            return Optional.empty();
        }  
    }
    
    private Boolean checkTopicName(final String topicNametoSearch) {
        return this.mainHandler.containsKey(topicNametoSearch);
    }
}
