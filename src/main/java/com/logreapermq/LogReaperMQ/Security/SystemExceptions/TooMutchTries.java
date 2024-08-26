package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class TooMutchTries extends RuntimeException {
    private String errorMesage;

    public TooMutchTries(final String message) {
        super(message);
        this.errorMesage = message;
    }
}
