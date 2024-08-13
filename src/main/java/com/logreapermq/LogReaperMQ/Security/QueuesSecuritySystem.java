package com.logreapermq.LogReaperMQ.Security;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class QueueSecuritySystem {
    
    @Scheduled(fixedRate = 3000)
    public SystemErrorsBinder checkQueuesDimension() {
        return null;
    }
}
