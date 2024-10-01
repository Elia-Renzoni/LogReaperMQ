package com.logreapermq.LogReaperMQ.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;

/*
 * Storage System
 */
@EntityScan
@EnableScheduling
public class Storage {
    @Autowired
    private TopicHandler handler;

    @Scheduled(fixedRate = 10000)
    public void storeQueue() {
        System.out.println("Storage System on...");

    }
}
