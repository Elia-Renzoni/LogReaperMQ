package com.logreapermq.LogReaperMQ.Network;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.logreapermq.LogReaperMQ.Security.SystemExceptions.InvalidQueueType;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.QueueTypeAlreadyExist;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchElements;

@ControllerAdvice
public class LogReaperMQExceptionHandler {
    
    @ExceptionHandler(TooMutchElements.class)
    public ResponseEntity<Object> handleTooMutchElementException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(QueueTypeAlreadyExist.class)
    public ResponseEntity<Object> handleQueueTypeAlreadyExistException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidQueueType.class)
    public ResponseEntity<Object> handleInvalidQueueTypeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
