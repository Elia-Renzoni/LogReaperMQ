package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class QueueTooHot extends RuntimeException {
    private String message;

    public QueueTooHot(final String message) {
        super(message);
        this.message = message;
    }
}
