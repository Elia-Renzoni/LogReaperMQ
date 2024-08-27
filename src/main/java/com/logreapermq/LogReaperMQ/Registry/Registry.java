package com.logreapermq.LogReaperMQ.Registry;


import java.util.Set;

import lombok.Getter;

import java.util.List;

@Getter
public class Registry {
    private Set<String> topics;
    private Set<String> queues;

    public Registry(List<String> topics, List<String> queues) {
        this.topics = Set.copyOf(topics);
        this.queues = Set.copyOf(queues);
    }
}
