package com.logreapermq.LogReaperMQ.Network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.QueueTooHot;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownQueue;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownTopic;
import com.logreapermq.LogReaperMQ.Wrappers.LogMessage;

@RestController
@RequestMapping(path = "reapermq/logs")
public class LogReceiverController {
    @Autowired
    private TopicHandler handler;
    
    @PostMapping(path = "/send", consumes = "application/json")
    public ResponseEntity<Void> logMessage(@RequestBody LogMessage message) throws RuntimeException {
        SystemErrorsBinder op = handler.addLog(message.getTopic(), message.getQueue(), message.getMessage());
        if (op == SystemErrorsBinder.UNKNOWN_QUEUE) {
            throw new UnknownQueue("Unknown Queue!");
        } else if (op == SystemErrorsBinder.UNKNOWN_TOPIC) {
            throw new UnknownTopic("Unknown Topic!");
        } else if (op == SystemErrorsBinder.QUEUE_TOO_HOT) {
            throw new QueueTooHot("Queue full of messages");
        }
        return ResponseEntity.ok().build();
    }
}
