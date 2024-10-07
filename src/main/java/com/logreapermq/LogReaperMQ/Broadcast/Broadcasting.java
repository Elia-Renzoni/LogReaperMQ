package com.logreapermq.LogReaperMQ.Broadcast;

import java.util.LinkedList;
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
       List<QueuesManager> managers = new LinkedList<>();

        /*
         * assign each thread three different topics
         */
       for (var topic : dTopics) {
            managers.add(this.topics.getTopicHandler().get(topic));
            if (managers.size() == 3) {
                this.sender.sendToSubscribers(managers);
                managers.clear();
            }
       }

       return SystemErrorsBinder.OK_STATUS; 
    }
}
