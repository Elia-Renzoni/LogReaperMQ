package com.logreapermq.LogReaperMQ.Network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Wrappers.LogMessage;

@RestController
@RequestMapping(path = "reapermq/logs")
public class LogReceiverController {
    @Autowired
    private TopicHandler handler;
    
    @PostMapping(path = "/send", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> logMessage(@RequestBody LogMessage message) {
        SystemErrorsBinder op = handler.addLog(message.getTopic(), message.getQueue(), message.getMessage());
        return ResponseEntity.ok().build();
    }
    
}
