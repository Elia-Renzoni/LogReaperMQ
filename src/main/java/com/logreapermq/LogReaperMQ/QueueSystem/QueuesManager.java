package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;


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
            .anyMatch(n -> n.getSubtopicType().equals(queueType));
        
        Boolean isTopicAlreadyExist = this.checkIfTopicExist(queueType);
        
        if (!(isTypeValid)) {
            return SystemErrorsBinder.INVALID_QUEUE_TYPE;
        }

        if (isTopicAlreadyExist) {
            return SystemErrorsBinder.QUEUE_TYPE_ALREADY_EXSIT;
        }

        this.topicQueues.add(new QueueEnvironment(queueType));
        return SystemErrorsBinder.OK_STATUS;
    }

    public SystemErrorsBinder deleteQueue(final String queueType) {
        Boolean isTopicExist = this.checkIfTopicExist(queueType);
        
        if (!(isTopicExist)) {
            return SystemErrorsBinder.UNKNOWN_QUEUE;
        }

        this.topicQueues.removeIf(n -> n.getQueueName().equals(queueType));
        return SystemErrorsBinder.OK_STATUS;
    }

    public Tuple<Boolean, QueueEnvironment> searchQueue(final String queue) {
        Boolean result = this.checkIfTopicExist(queue);
        QueueEnvironment queueType = null;

        if (!(result)) {
            return new Tuple<Boolean,QueueEnvironment>(result, queueType);
        }

        for (var q : this.topicQueues) {
            if (q.getQueueName().equals(queue)) {
                queueType = q;
            } 
        }

        return new Tuple<Boolean,QueueEnvironment>(result, queueType);
    }

    public Set<QueueEnvironment> getTopicQueues() {
        return this.topicQueues;
    }
    
    private Boolean checkIfTopicExist(final String queueType) {
        return this.topicQueues.stream()
            .anyMatch(n -> n.getQueueName().equals(queueType));
    }    
}
