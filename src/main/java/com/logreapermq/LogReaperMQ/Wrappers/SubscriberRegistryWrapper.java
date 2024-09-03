package com.logreapermq.LogReaperMQ.Wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class SubscriberRegistryWrapper {
    private String subscriberInfo;
    private List<String> topics;
    private List<String> queues;
    
    @JsonCreator
    public SubscriberRegistryWrapper(@JsonProperty("id") String subscriberInfo, 
                              @JsonProperty("topics") List<String> topics, 
                              @JsonProperty("queues") List<String> queues) {
        this.subscriberInfo = subscriberInfo;
        this.topics = List.copyOf(topics);
        this.queues = List.copyOf(queues);
    }
}
