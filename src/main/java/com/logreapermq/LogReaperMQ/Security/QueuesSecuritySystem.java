package com.logreapermq.LogReaperMQ.Security;

import java.util.List;
import java.util.Set;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;



@Configuration
@EnableScheduling
public class QueuesSecuritySystem {
    @Autowired
    private TopicHandler topicManager;
    @Autowired
    private AsyncQueuesDimControllers asyncQueueController;

    @Scheduled(fixedDelay = 9000, initialDelay = 9000)
    public void checkQueuesDimension() {
        System.out.println("Security System on...");
        Set<String> topics = this.topicManager.getTopicHandler().keySet();
        List<QueuesManager> managers = new LinkedList<>();
        
        /*
         * assign each thread three different topics
         */
        for (var topicToCheck : topics) {
            managers.add(this.topicManager.getTopicHandler().get(topicToCheck));
            if (managers.size() >= 3) {
                this.asyncQueueController.checkQueueDimension(managers);
                managers.clear();
            }
        }
    }
}