package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;


import com.logreapermq.LogReaperMQ.Security.SubTopicTypes;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

// class to handle code associated with a topic
// each topic can have more queues 
// and the queues must be valid with respect to the indications 
// of the class SubTopicTypes
public class QueuesManager {
    // queues
    private Set<QueueEnvironment> topicQueues;

    public QueuesManager() {
        this.topicQueues = new HashSet<>();
    }

    // method to add a new queue
    // @return execution result
    // @param queue type
    public SystemErrorsBinder addQueue(final String queueType) {
        // check if the queue type is valid.
        Boolean isTypeValid = Stream.of(SubTopicTypes.values())
            .anyMatch(n -> n.getSubtopicType().equals(queueType));
        
        // check if a queue already exist
        Boolean isTopicAlreadyExist = this.checkIfQueueExist(queueType);
        
        if (!(isTypeValid)) {
            return SystemErrorsBinder.INVALID_QUEUE_TYPE;
        }

        if (isTopicAlreadyExist) {
            return SystemErrorsBinder.QUEUE_TYPE_ALREADY_EXIST;
        }

        this.topicQueues.add(new QueueEnvironment(queueType));
        return SystemErrorsBinder.OK_STATUS;
    }

    // method to delete a queue
    // @return execution result
    // @param queue type
    public SystemErrorsBinder deleteQueue(final String queueType) {
        // check if the queue exist in the system
        Boolean isTopicExist = this.checkIfQueueExist(queueType);
        
        if (!(isTopicExist)) {
            return SystemErrorsBinder.UNKNOWN_QUEUE;
        }

        this.topicQueues.removeIf(n -> n.getQueueName().equals(queueType));
        return SystemErrorsBinder.OK_STATUS;
    }

    public Tuple<Boolean, QueueEnvironment> searchQueue(final String queue) {
        Boolean result = this.checkIfQueueExist(queue);
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
    
    // method to check if a queue is present in the system
    private Boolean checkIfQueueExist(final String queueType) {
        return this.topicQueues.stream()
            .anyMatch(n -> n.getQueueName().equals(queueType));
    }    
}
