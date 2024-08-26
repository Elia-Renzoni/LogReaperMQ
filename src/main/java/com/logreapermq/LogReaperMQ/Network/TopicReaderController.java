package com.logreapermq.LogReaperMQ.Network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logreapermq.LogReaperMQ.Registry.SubRegistry;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownQueue;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownTopic;
import com.logreapermq.LogReaperMQ.Wrappers.SubscriberRegistry;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "reapermq/subscribers")
public class TopicReaderController {

    @Autowired
    private SubRegistry registrer;

    @PostMapping("/register")
    public ResponseEntity<Object> registerCallBackMethods(@Valid @RequestBody SubscriberRegistry registry) throws RuntimeException {
        SystemErrorsBinder op = registrer.entry(registry.getSubscriberInfo(), registry.getTopics(), registry.getQueues());
        if (op == SystemErrorsBinder.UNKNOWN_TOPIC) {
            throw new UnknownTopic("Unknown Topic!");
        } else if (op == SystemErrorsBinder.UNKNOWN_QUEUE) {
            throw new UnknownQueue("Unknown Queue");
        }
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body("Callback Method Succefully Created!");
    }
    
    @DeleteMapping("/deregister/{topic}")
    public ResponseEntity<Void> deregisterFromTopic(@PathVariable String topicName) throws RuntimeException {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deregister/{topic}/{queue}")
    public ResponseEntity<Void> deregisterFromQueue(@PathVariable String topicName, @PathVariable String queueName) throws RuntimeException {
        return ResponseEntity.noContent().build();
    }
}
