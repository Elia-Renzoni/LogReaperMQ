package com.logreapermq.LogReaperMQ.Registry;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

import java.util.HashMap;

@Service
@Scope("sigleton")
public class SubRegistry {
    private Map<String, Registry> subcribersCallBack;
    @Autowired
    private TopicHandler handler;

    public SubRegistry() {
        this.subcribersCallBack = new HashMap<>();
    }
    
    public SystemErrorsBinder entry(final String subId, final List<String> topics, final List<String> queues) {
        // check potential errors.
        SystemErrorsBinder topicOp = handler.checkTopicsForSubscribers(topics);
        SystemErrorsBinder queueOp = handler.checkQueuesForSubscribers(queues);

        if (topicOp != SystemErrorsBinder.OK_STATUS) {
           return topicOp; 
        }

        if (queueOp != SystemErrorsBinder.OK_STATUS) {
            return queueOp;
        }

        this.subcribersCallBack.put(subId, new Registry(topics, queues));

        return SystemErrorsBinder.OK_STATUS;
    }
    
    public SystemErrorsBinder deleteEntry(final String topic, final String queue) {

        if (!(queue.isEmpty())) {
            SystemErrorsBinder queueOp = handler.checkQueuesForSubscribers(List.of(queue));
            if (queueOp != SystemErrorsBinder.OK_STATUS) {
                return queueOp;
            }
        }

        SystemErrorsBinder topicOp = handler.checkTopicsForSubscribers(List.of(queue));
        
        if (topicOp != SystemErrorsBinder.OK_STATUS) {
            return topicOp;
        }

        // TODO
        this.subcribersCallBack.remove();

        return SystemErrorsBinder.OK_STATUS;
    }
}
