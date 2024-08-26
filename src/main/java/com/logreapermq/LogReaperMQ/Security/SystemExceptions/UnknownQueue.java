package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class UnknownQueue extends RuntimeException {
    private String message;

    public UnknownQueue(final String message) {
        super(message);
        this.message = message;
    }
}
