package com.logreapermq.LogReaperMQ.Network;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.logreapermq.LogReaperMQ.Security.SystemExceptions.InvalidQueueType;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.QueueTooHot;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.QueueTypeAlreadyExist;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchElements;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchTries;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TopicAlreadyExist;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownItem;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownQueue;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownTopic;

@ControllerAdvice
public class LogReaperMQExceptionHandler {
    
    @ExceptionHandler(TooMutchElements.class)
    public ResponseEntity<Object> handleTooMutchElementException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.internalServerError()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(QueueTypeAlreadyExist.class)
    public ResponseEntity<Object> handleQueueTypeAlreadyExistException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(InvalidQueueType.class)
    public ResponseEntity<Object> handleInvalidQueueTypeException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(UnknownTopic.class)
    public ResponseEntity<Object> handleUnknownTopicException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(UnknownQueue.class)
    public ResponseEntity<Object> handleUnknownQueueException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(TopicAlreadyExist.class)
    public ResponseEntity<Object> handleTopicAlreadyExistException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }

    @ExceptionHandler(TooMutchTries.class)
    public ResponseEntity<Object> handleTooMutchTriesException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.internalServerError()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }

    @ExceptionHandler(UnknownItem.class)
    public ResponseEntity<Object> handleUnknownItemException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }

    @ExceptionHandler(QueueTooHot.class)
    public ResponseEntity<Object> handleQueueTooHotException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.internalServerError()
                .header("Content-Type", "application/json")
                .body(ex.getMessage());
    }
}
