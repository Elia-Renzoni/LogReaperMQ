package com.logreapermq.LogReaperMQ.Wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubscriberRegistry {
    private String topic;
    private String queueName;
}
