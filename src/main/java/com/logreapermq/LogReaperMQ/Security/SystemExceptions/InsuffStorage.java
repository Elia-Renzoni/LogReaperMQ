package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class InsuffStorage extends RuntimeException {
    private String message;

    public InsuffStorage(final String message) {
        super(message);
        this.message = message;
    }
}
