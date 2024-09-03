package com.logreapermq.LogReaperMQ.Wrappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class LogMessageWrapper {
    private String message;
    private String topic;
    private String queue;

    @JsonCreator
    public LogMessageWrapper(@JsonProperty("message") String msg, 
                      @JsonProperty("topic") String topic, 
                      @JsonProperty("queue") String queue) {
        this.message = msg;
        this.topic = topic;
        this.queue = queue;
    }
}
