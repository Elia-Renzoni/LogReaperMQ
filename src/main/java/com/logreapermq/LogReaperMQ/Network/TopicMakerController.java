package com.logreapermq.LogReaperMQ.Network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.InvalidQueueType;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.QueueTypeAlreadyExist;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchElements;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TopicAlreadyExist;
import com.logreapermq.LogReaperMQ.Wrappers.QueueMakerWrapper;
import com.logreapermq.LogReaperMQ.Wrappers.TopicMakerWrapper;

import jakarta.validation.Valid;

// this RestController handle the making
// of new topics and queues. 

@RestController
@RequestMapping(path = "reapermq/maker")
public class TopicMakerController {
    @Autowired
    private TopicHandler queueHolder;
    
    @PostMapping(value = "/topic", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createTopic(@Valid @RequestBody TopicMakerWrapper requestBody) throws RuntimeException {
        SystemErrorsBinder operationResult = queueHolder.addNewTopic(requestBody.getTopic()); 

        if (operationResult == SystemErrorsBinder.TOO_MUTCH_ELEMENTS) {
            throw new TooMutchElements("The Topic Handler has too element");
        } else if (operationResult == SystemErrorsBinder.TOPIC_ALREADY_EXIST) {
            throw new TopicAlreadyExist("The Topic " + requestBody.getTopic() + " alredy exist in the system");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body("The topic" + requestBody.getTopic() + " has been created succesfully");
    }

    @PostMapping(value = "/queue", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createQueue(@Valid @RequestBody QueueMakerWrapper requestBody) throws RuntimeException {
        SystemErrorsBinder operationResult = queueHolder.addQueue(requestBody.getTopic(), requestBody.getQueue());

        if (operationResult == SystemErrorsBinder.INVALID_QUEUE_TYPE) {
            throw new InvalidQueueType("The Queue Type Is Not Valid for the System");
        } else if (operationResult == SystemErrorsBinder.QUEUE_TYPE_ALREADY_EXSIT) {
            throw new QueueTypeAlreadyExist("The Queue Already Exist in the System");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body("The queue " + requestBody.getQueue() + " ha been created succesfully");
    }
    
}
