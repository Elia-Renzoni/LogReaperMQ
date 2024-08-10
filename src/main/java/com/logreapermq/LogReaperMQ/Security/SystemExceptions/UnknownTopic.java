package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class UnknownTopic extends RuntimeException {
    private String message;

    public UnknownTopic(final String message) {
        super(message);
        this.message = message;
    }
    
}
