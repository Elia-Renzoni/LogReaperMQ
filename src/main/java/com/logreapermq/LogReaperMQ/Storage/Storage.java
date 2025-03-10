package com.logreapermq.LogReaperMQ.Storage;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;

/*
 * Storage System
 */
@Configuration
@EnableScheduling
public class Storage {
    @Autowired
    private TopicHandler handler;
    @Autowired
    private AsyncStorage asyncStorageWorkers;

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void storeQueue() {
        System.out.println("Storage System on...");

        Set<String> keys = this.handler.getTopicHandler().keySet();
        List<ManagersPairStructure<QueuesManager, String>> managers = new LinkedList<>();
        
        /*
         * assign each thread three different topics
         */
        for (var key : keys) {
            managers.add(new ManagersPairStructure<QueuesManager,String>(this.handler.getTopicHandler().get(key), key));
            if (managers.size() >= 3) {
                // create the thread
                this.asyncStorageWorkers.storeAndDelete(managers);
                managers.clear();
            }

            if (keys.size() == 1) 
                this.asyncStorageWorkers.storeAndDelete(managers);
        }
    }
}
