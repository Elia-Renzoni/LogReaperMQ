package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class UnknownItem extends RuntimeException {
    private String message;

    public UnknownItem(final String message) {
        super(message);
        this.message = message;
    }
}
