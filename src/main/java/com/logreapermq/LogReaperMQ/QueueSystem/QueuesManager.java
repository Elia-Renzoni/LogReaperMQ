package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

public class QueuesManager {
    private Set<QueueEnvironment> topicQueues;

    public QueuesManager() {
        this.topicQueues = new HashSet<>();
    }

    public SystemErrorsBinder addQueue(final String queueType) {
        return null;
    }
    
}
