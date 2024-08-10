package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class TooMutchElements extends RuntimeException {
    private String message;

    public TooMutchElements(final String message) {
        super(message);
        this.message = message;
    }
}
