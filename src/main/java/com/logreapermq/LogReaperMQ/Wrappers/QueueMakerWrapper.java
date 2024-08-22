package com.logreapermq.LogReaperMQ.Wrappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueMakerWrapper {
    private String topic;
    private String queue;

    @JsonCreator
    public QueueMakerWrapper(@JsonProperty("topic") String topic, @JsonProperty("queue") String queue) {
        this.topic = topic;
        this.queue = queue;
    }
}
