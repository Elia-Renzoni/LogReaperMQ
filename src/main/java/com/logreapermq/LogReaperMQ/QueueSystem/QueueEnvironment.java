package com.logreapermq.LogReaperMQ.QueueSystem;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import com.logreapermq.LogReaperMQ.Registry.Subscriber;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

// this class manage the log queues

public class QueueEnvironment {
    // quque of logs
    private Set<Message> queue;

    // is a flag indicating whether new messages 
    // can be added to the queue or not
    // dirtyBit -> true = new messages can be added
    // dirtyBit -> false = the queue is full of messages
    private Boolean dirtyBit;
    private Boolean subscriberCallBackMethod;
    private Set<Subscriber> subscribersHostAndPort;
    
    // queue name. The names must be identical to those given by SubTopicTypes
    private String queueName;

    public QueueEnvironment(final String name) {
        this.queue = new HashSet<>();
        this.queueName = name;
        this.dirtyBit = true;
        this.subscribersHostAndPort = new HashSet<>();
    }

    // method to add new logs to the queue
    // @return execution result of the operation
    // @param log to add
    public SystemErrorsBinder addItem(final String item) {
        if (!(this.dirtyBit)) {
            return SystemErrorsBinder.QUEUE_TOO_HOT;
        }
        
        this.queue.add(new Message(item, true));
        return SystemErrorsBinder.OK_STATUS;
    }

    public void addSubscriber(final String host, final Integer port) {
        this.subscribersHostAndPort.add(new Subscriber(host, port));
    }

    public SystemErrorsBinder deleteSubscriber(final String host, final Integer port) {
        for (var subscriber : this.subscribersHostAndPort) {
            if (subscriber.getHost().equals(host) && subscriber.getPort() == port) {
                this.subscribersHostAndPort.remove(subscriber);
            } else {
                return SystemErrorsBinder.UNKNOWN_ITEM;
            }
        }
        return SystemErrorsBinder.OK_STATUS;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public Set<Message> getMessageQueue() {
        return this.queue;
    }

    public void setSubscriberCallBackMethod(final Boolean flag) {
        this.subscriberCallBackMethod = flag;
    }

    public Set<Subscriber> getSubscriberHostAndPorts() {
        return this.subscribersHostAndPort;
    }
    
    // method to delete a log message
    // @return execution result
    // @param log to delete
    public SystemErrorsBinder deleteItem(final String item) {
        
        // check if the log message exist in the queue
        Boolean op = this.queue.stream()
            .anyMatch(n -> n.getMessage().equals(item));
        
        if (!(op)) {
            return SystemErrorsBinder.UNKNOWN_ITEM;
        }
        
        Message msg = null;
        
        // return the message to delete
        for (Message m : this.queue) {
            if (m.getMessage().equals(item)) {
                msg = m;
                break;
            }
        }
        
        this.queue.remove(msg);
        return SystemErrorsBinder.OK_STATUS;
    }

    // method to calculate the queue dimension
    // @return MegaBytes dimension of the queue
    public Long getQueueMemoryDimension() {
        Long dimension = 0L;
        for (var item : this.queue) {
            var itemBytes = item.getMessage().getBytes(Charset.defaultCharset());
            dimension += itemBytes.length;
        }
        return dimension;
    }

    public void setDirtyBitToFalse() {
        // no more logs can be added
        this.dirtyBit = false;
    }

    public void setDirtyBitToTrue() {
        // new logs can be added
        this.dirtyBit = true;
    }
}
