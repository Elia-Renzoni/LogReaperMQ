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
    private String subscriberHost;
    private Integer subscriberListenPort;
    private List<String> topics;
    private List<String> queues;
    
    @JsonCreator
    public SubscriberRegistryWrapper(@JsonProperty("id") String subscriberHost, 
                             @JsonProperty("port") Integer port,
                              @JsonProperty("topics") List<String> topics, 
                              @JsonProperty("queues") List<String> queues) {
        this.subscriberHost = subscriberHost;
        this.subscriberListenPort = port;
        this.topics = List.copyOf(topics);
        this.queues = List.copyOf(queues);
    }
}
