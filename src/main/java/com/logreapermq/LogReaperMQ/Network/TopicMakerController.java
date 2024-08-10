package com.logreapermq.LogReaperMQ.Network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.InvalidQueueType;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.QueueTypeAlreadyExist;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchElements;
import com.logreapermq.LogReaperMQ.Wrappers.QueueMakerWrapper;
import com.logreapermq.LogReaperMQ.Wrappers.TopicMakerWrapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "reapermq/maker")
public class TopicMakerController {
    @Autowired
    private TopicHandler queueHolder;

    @PostMapping("/topic")
    public ResponseEntity<Object> createTopic(@Valid @RequestBody TopicMakerWrapper requestBody) throws RuntimeException {
        SystemErrorsBinder operationResult = queueHolder.addNewTopic(requestBody.getTopic()); 
        if (operationResult == SystemErrorsBinder.TOO_MUCH_ELEMENTS) {
            throw new TooMutchElements("The Topic Handler has too element");
        }
        return new ResponseEntity<>("New Topic Has Created Succesfully", HttpStatus.CREATED);
    }

    @PostMapping("/queue")
    public ResponseEntity<Object> createSubtopic(@Valid @RequestBody QueueMakerWrapper requestBody) throws RuntimeException {
        SystemErrorsBinder operationResult = queueHolder.addQueue(requestBody.getTopic(), requestBody.getQueue());
        if (operationResult == SystemErrorsBinder.INVALID_QUEUE_TYPE) {
            throw new InvalidQueueType("The Queue Type Is Not Valid for the System");
        } else if (operationResult == SystemErrorsBinder.QUEUE_TYPE_ALREADY_EXSIT) {
            throw new QueueTypeAlreadyExist("The Queue Already Exist in the System");
        }
        return new ResponseEntity<>("New Queue Has Been Created Succesfully", HttpStatus.CREATED);
    }
    
}
