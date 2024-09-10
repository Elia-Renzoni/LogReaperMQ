package com.logreapermq.LogReaperMQ.Registry;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;


@Service
@Scope("singleton")
public class SubRegistry {
    @Autowired
    private TopicHandler handler;
    
    public synchronized SystemErrorsBinder entry(final String subId, final Integer subPort, final List<String> topics, final List<String> queues) {
        // check potential errors.
        SystemErrorsBinder topicOp = handler.checkTopicsForSubscribers(topics);
        SystemErrorsBinder queueOp = handler.checkQueuesForSubscribers(topics, queues);

        if (topicOp != SystemErrorsBinder.OK_STATUS) {
           return topicOp; 
        }

        if (queueOp != SystemErrorsBinder.OK_STATUS) {
            return queueOp;
        }

        this.handler.addDirtyTopics(topics);

        for (var topicToSearch : topics) {
            if (this.handler.getTopicHandler().containsKey(topicToSearch)) {
                QueuesManager queuesToRegistry = this.handler.getTopicHandler().get(topicToSearch);
                for (var queue : queues) {
                    for (var queueLog : queuesToRegistry.getTopicQueues()) {
                        if (queueLog.getQueueName().equals(queue)) {
                            queueLog.setSubscriberCallBackMethod(true);
                            queueLog.addSubscriber(subId, subPort);
                        }
                    }
                }
            } else {
                return SystemErrorsBinder.UNKNOWN_TOPIC;
            }
        }
        return SystemErrorsBinder.OK_STATUS;
    }
    
    // delete subscriber from a topic queue.
    public synchronized SystemErrorsBinder deleteEntry(final String host, final Integer port, final String topic, final String queue) {
        SystemErrorsBinder resultOpTopicSearch = this.handler.checkTopicsForSubscribers(List.of(topic));
        SystemErrorsBinder resultOpQueueSearch = this.handler.checkQueuesForSubscribers(List.of(topic), List.of(queue));
        SystemErrorsBinder removeOpResult = null;

        if (resultOpTopicSearch != SystemErrorsBinder.OK_STATUS) {
            return resultOpTopicSearch;
        }

        if (resultOpQueueSearch != SystemErrorsBinder.OK_STATUS) {
            return resultOpQueueSearch;
        }

        this.handler.deleteDirtyTopics(topic);

        if (this.handler.getTopicHandler().containsKey(topic)) {
            QueuesManager queuesToRegistry = this.handler.getTopicHandler().get(topic);
            for (var queueLog : queuesToRegistry.getTopicQueues()) {
                if (queueLog.getQueueName().equals(queue)) {
                    removeOpResult = queueLog.deleteSubscriber(host, port);
                }
            }
        }

        return removeOpResult;
    }
}
