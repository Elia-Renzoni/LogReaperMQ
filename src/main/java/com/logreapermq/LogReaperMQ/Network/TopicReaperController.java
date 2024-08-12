package com.logreapermq.LogReaperMQ.Network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownQueue;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownTopic;

@RestController
@RequestMapping(path = "reapermq/breaker")
public class TopicReaperController {
    @Autowired
    private TopicHandler queueHolder;
    
    @DeleteMapping("/{topicName}")
    public ResponseEntity<Void> deleteTopic(@PathVariable String topicName) throws RuntimeException {
        SystemErrorsBinder opResult = queueHolder.deleteTopic(topicName);
        if (opResult == SystemErrorsBinder.UNKNOWN_TOPIC) {
            throw new UnknownTopic("The Topic" + topicName + " doesn't exist in the system");
        }
        return ResponseEntity.noContent()
               .build();
    }

    @DeleteMapping("/{topicName}/{queueName}")
    public ResponseEntity<Void> deleteQueue(@PathVariable String topicName, @PathVariable String queueName) throws RuntimeException {
        SystemErrorsBinder opResult = queueHolder.deleteQueue(topicName, queueName);
        if (opResult == SystemErrorsBinder.UNKNOWN_QUEUE) {
            throw new UnknownQueue("The Queue " + queueName + " doesn't exist in the system");
        } else if (opResult == SystemErrorsBinder.UNKNOWN_TOPIC) {
            throw new UnknownTopic("The Topic " + topicName + " doesn't exist in the system");
        }
        return ResponseEntity.noContent()
                .build();
    }
}
