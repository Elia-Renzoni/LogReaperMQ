package com.logreapermq.LogReaperMQ.QueueSystem;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

public class QueueEnvironment {
    private Set<Message> queue;
    private Boolean dirtyBit;
    private String queueName;

    public QueueEnvironment(final String name) {
        this.queue = new HashSet<>();
        this.queueName = name;
        this.dirtyBit = true;
    }

    public SystemErrorsBinder addItem(final String item) {
        if (!(this.dirtyBit)) {
            return SystemErrorsBinder.QUEUE_TOO_HOT;
        }

        if (this.queue.contains(item)) {
            return SystemErrorsBinder.ITEM_ALREADY_EXIST;
        }
        this.queue.add(new Message(item));
        return SystemErrorsBinder.OK_STATUS;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public Set<Message> getMessageQueue() {
        return this.queue;
    }
    
    public SystemErrorsBinder deleteItem(final String item) {
        if (!(this.queue.contains(item))) {
            return SystemErrorsBinder.UNKNOWN_ITEM; 
        }
        this.queue.remove(item);
        return SystemErrorsBinder.OK_STATUS;
    }

    public Long getQueueMemoryDimension() {
        Long dimension = 0L;
        for (var item : this.queue) {
            var itemBytes = item.getMessage().getBytes(Charset.defaultCharset());
            dimension += itemBytes.length;
        }
        return dimension;
    }

    public void setDirtyBitToFalse() {
        this.dirtyBit = false;
    }

    public void setDirtyBitToTrue() {
        this.dirtyBit = true;
    }
}
