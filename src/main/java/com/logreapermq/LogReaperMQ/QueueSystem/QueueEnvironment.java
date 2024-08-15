package com.logreapermq.LogReaperMQ.QueueSystem;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

public class QueueEnvironment {
    private Set<String> queue;
    private Boolean dirtyBit;
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

    public Set<String> getMessageQueue() {
        return this.queue;
    }
    
    public SystemErrorsBinder deleteItem(final String item) {
        if (!(this.queue.contains(item))) {
            return SystemErrorsBinder.UNKNOWN_ITEM; 
        }
        this.queue.remove(item);
        return SystemErrorsBinder.OK_STATUS;
    }

    public Integer getQueueMemoryDimension() {
        Integer queueDimension;

        for (var item : this.queue) {
            queueDimension += item.getBytes(Charset.defaultCharset());
        }

        return queueDimension;
    }

    public void setDirtyBitToFalse() {
        this.dirtyBit = false;
    }

    public void setDirtyBitToTrue() {
        this.dirtyBit = true;
    }
}
