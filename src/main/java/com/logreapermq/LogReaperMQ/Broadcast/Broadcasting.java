package com.logreapermq.LogReaperMQ.Broadcast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

@Configuration
@EnableScheduling
public class Broadcasting {
    @Autowired
    private TopicHandler topics;
    @Autowired
    private AsyncSender sender;
    
    @Scheduled(fixedDelay = 2000, initialDelay = 2000)
    public SystemErrorsBinder broadcast() { 
       System.out.println("Broadcast on...");
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
