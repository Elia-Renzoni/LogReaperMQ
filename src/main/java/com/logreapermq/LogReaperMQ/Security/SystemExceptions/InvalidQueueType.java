package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class InvalidQueueType extends RuntimeException {
    private String message;

    public InvalidQueueType(final String message) {
        super(message);
        this.message = message;
    }
}
