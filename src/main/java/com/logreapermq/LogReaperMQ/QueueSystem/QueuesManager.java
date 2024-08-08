package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.logreapermq.LogReaperMQ.Security.SubTopicTypes;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

public class QueuesManager {
    private Set<QueueEnvironment> topicQueues;

    public QueuesManager() {
        this.topicQueues = new HashSet<>();
    }

    public SystemErrorsBinder addQueue(final String queueType) {
        // check if the queue type is valid.
        Boolean isTypeValid = Stream.of(SubTopicTypes.values())
            .filter(n -> n.getSubtopicType().equals(queueType))
            .anyMatch(null);
        
        Boolean isTopicAlreadyExist = this.topicQueues.stream()
            .filter(n -> n.getQueueName().equals(queueType))
            .anyMatch(null);
        
        if (!(isTypeValid)) {
            return SystemErrorsBinder.INVALID_QUEUE_TYPE;
        }

        if (!(isTopicAlreadyExist)) {
            return SystemErrorsBinder.QUEUE_TYPE_ALREADY_EXSIT;
        }

        this.topicQueues.add(new QueueEnvironment(queueType));
        return SystemErrorsBinder.OK_STATUS;
    }

    public SystemErrorsBinder deleteQueue(final String queueType) {
        Boolean isTopicExist = this.topicQueues.stream()
            .filter(n -> n.getQueueName().equals(queueType))
            .anyMatch(null);
        
        if (!(isTopicExist)) {
            return SystemErrorsBinder.UNKNOWN_QUEUE;
        }

        this.topicQueues.removeIf(n -> n.getQueueName().equals(queueType));
        return SystemErrorsBinder.OK_STATUS;
    }
    
}
