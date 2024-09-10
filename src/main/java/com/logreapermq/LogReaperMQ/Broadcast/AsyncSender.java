package com.logreapermq.LogReaperMQ.Broadcast;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.logreapermq.LogReaperMQ.QueueSystem.Message;
import com.logreapermq.LogReaperMQ.Registry.Subscriber;

// Best-Effort Broadcast Implementation for LogReaperMQ
@EnableAsync
public class AsyncSender {
   
    @Async
    public void sendToSubscribers(final List<Message> logs, final List<Subscriber> subsribers) {

    }

    public void sendTo(final Message message, final String host, final Integer port) {

    }
}
