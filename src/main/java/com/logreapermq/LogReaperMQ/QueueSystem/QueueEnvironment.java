package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.LinkedList;
import java.util.List;

public class QueueEnvironment {
    private List<String> queue;
    private String queueName;

    public QueueEnvironment(final String name) {
        this.queue = new LinkedList<>();
        this.queueName = name;
    }

    public void addItem() {

    }

    public String getQueueName() {
        return this.queueName;
    }
    
    public void deleteItem() {

    }
}
