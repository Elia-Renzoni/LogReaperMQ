package com.logreapermq.LogReaperMQ.Broadcast;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.logreapermq.LogReaperMQ.QueueSystem.Message;
import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

@EnableScheduling
public class Broadcasting {
    @Autowired
    private TopicHandler topics;
    @Autowired
    private AsyncSender sender;
    
    @Scheduled(fixedRate = 2000)
    public SystemErrorsBinder broadcast() { 
       List<String> dTopics = this.topics.getDirtyTopics(); 

       for (var topic : dTopics) {
          QueuesManager manager = this.topics.getTopicHandler().get(topic);
          for (var queue : manager.getTopicQueues()) {
              this.sender.sendToSubscribers(queue.getMessageQueue(), queue.getSubscriberHostAndPorts());
          }
       }
       return SystemErrorsBinder.OK_STATUS; 
    }
}
