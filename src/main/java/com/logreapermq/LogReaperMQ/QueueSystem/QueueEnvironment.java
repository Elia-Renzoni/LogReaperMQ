package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.HashSet;
import java.util.Set;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

public class QueueEnvironment {
    private Set<String> queue;
    private String queueName;

    public QueueEnvironment(final String name) {
        this.queue = new HashSet<>();
        this.queueName = name;
    }

    public SystemErrorsBinder addItem(final String item) {
        if (this.queue.contains(item)) {
            return SystemErrorsBinder.ITEM_ALREADY_EXIST;
        }
        this.queue.add(item);
        return SystemErrorsBinder.OK_STATUS;
    }

    public String getQueueName() {
        return this.queueName;
    }
    
    public SystemErrorsBinder deleteItem(final String item) {
        if (!(this.queue.contains(item))) {
            return SystemErrorsBinder.UNKNOWN_ITEM; 
        }
        this.queue.remove(item);
        return SystemErrorsBinder.OK_STATUS;
    }
}
