package com.logreapermq.LogReaperMQ.Security.SystemExceptions;

import lombok.Getter;

@Getter
public class TopicAlreadyExist extends RuntimeException {
    private String message;

    public TopicAlreadyExist(final String message) {
        super(message);
        this.message = message;
    }
}
