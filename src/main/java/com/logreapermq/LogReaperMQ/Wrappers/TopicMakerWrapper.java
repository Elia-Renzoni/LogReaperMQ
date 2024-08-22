package com.logreapermq.LogReaperMQ.Wrappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class TopicMakerWrapper {
    private String topic;

    @JsonCreator
    public TopicMakerWrapper(@JsonProperty("topic") String topic) {
        this.topic = topic;
    }
}
