package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class ItemAlreadyExist extends RuntimeException {
    private String message;

    public ItemAlreadyExist(final String message) {
        super(message);
        this.message = message;
    }
}
