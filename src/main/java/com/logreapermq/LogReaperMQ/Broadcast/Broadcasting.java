package com.logreapermq.LogReaperMQ.Broadcast;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

@EnableScheduling
public class Broadcasting {
    
    @Scheduled(fixedRate = 2000)
    public SystemErrorsBinder broadcast() { 
       return SystemErrorsBinder.OK_STATUS; 
    }
    
}
