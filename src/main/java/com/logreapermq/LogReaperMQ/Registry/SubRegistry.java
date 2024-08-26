package com.logreapermq.LogReaperMQ.Registry;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchTries;

import java.util.HashMap;

@Service
@Scope("singleton")
public class SubRegistry {
    private Map<Subscriber, Registry> subcribersCallBack;
    @Autowired
    private TopicHandler handler;

    public SubRegistry() {
        this.subcribersCallBack = new HashMap<>();
    }
    
    public synchronized Tuple<SystemErrorsBinder, Integer> entry(final String subId, final List<String> topics, final List<String> queues) {
        // check potential errors.
        SystemErrorsBinder topicOp = handler.checkTopicsForSubscribers(topics);
        SystemErrorsBinder queueOp = handler.checkQueuesForSubscribers(queues);

        if (topicOp != SystemErrorsBinder.OK_STATUS) {
           return new Tuple<>(topicOp, null); 
        }

        if (queueOp != SystemErrorsBinder.OK_STATUS) {
            return new Tuple<>(queueOp, null);
        }

        Subscriber newSubscriber;
        Integer Id;
        try {
            newSubscriber = new Subscriber(subId);
            Id = newSubscriber.getId();
        } catch (TooMutchTries e) {
            return new Tuple<>(SystemErrorsBinder.TOO_MUTCH_TRIES, null);
        }

        this.subcribersCallBack.put(newSubscriber, new Registry(topics, queues));

        return new Tuple<>(SystemErrorsBinder.OK_STATUS, Id);
    }
    
    // delete subscriber from a topic.
    public synchronized SystemErrorsBinder deleteEntry(final Integer id) {
        Optional<Subscriber> toDelete = this.subcribersCallBack.entrySet().stream()
            .map(Map.Entry::getKey)
            .filter(k -> k.getId() == id)
            .findFirst();

        if (toDelete.isEmpty()) {
            return SystemErrorsBinder.UNKNOWN_ITEM;
        }
        this.subcribersCallBack.remove(toDelete.get());

        return SystemErrorsBinder.OK_STATUS;
    }

    // delete a queue
    public synchronized SystemErrorsBinder deleteQueue(final Integer id, final String queue) {
        Optional<Subscriber> subscriber = this.subcribersCallBack.entrySet().stream()
                .map(Map.Entry::getKey)
                .filter(k -> k.getId() == id)
                .findFirst();

        if (subscriber.isEmpty()) {
            return SystemErrorsBinder.UNKNOWN_ITEM;
        }

        for (var kv : this.subcribersCallBack.entrySet()) {
            if (kv.getKey().getId() == id) {
                if (kv.getValue().getQueues().remove(queue)) {
                    return SystemErrorsBinder.OK_STATUS;
                }
            }
        }

        return SystemErrorsBinder.UNKNOWN_QUEUE;
    }

    
}
