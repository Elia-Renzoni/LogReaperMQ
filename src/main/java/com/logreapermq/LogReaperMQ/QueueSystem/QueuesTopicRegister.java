package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public class QueuesTopicRegister {
    private List<String> queues;

    public QueuesTopicRegister() {
        this.queues = new ArrayList<>();
    }

    public void addQueue(final String queueName) {
        this.queues.add(queueName);
    }

    public void deleteQueue(final String queueName) {
        this.queues.remove(queueName);        
    }

    public void clearRegister(final String queueName) {
        this.queues.clear();
    }

    public Optional<String> searchForQueue(final String queueName) {
        return this.queues.stream()
            .filter(qName -> qName.equals(queueName))
            .findFirst();
    }
}
