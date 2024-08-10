package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class QueueTypeAlreadyExist extends RuntimeException {
    private String message;

    public QueueTypeAlreadyExist(final String message) {
        super(message);
        this.message = message;
    }
}
