package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

public class TooMutchTries extends Exception {
    private String errorMesage;

    public TooMutchTries(final String message) {
        super(message);
        this.errorMesage = message;
    }

    public String getErrorMessage() {
        return this.errorMesage;
    }
}
