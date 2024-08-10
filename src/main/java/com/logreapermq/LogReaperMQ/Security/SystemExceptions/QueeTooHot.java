package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class QueeTooHot extends RuntimeException {
    private String message;

    public QueeTooHot(final String message) {
        super(message);
        this.message = message;
    }
}
