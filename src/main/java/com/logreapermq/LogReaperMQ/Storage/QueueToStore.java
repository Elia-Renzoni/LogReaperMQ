package com.logreapermq.LogReaperMQ.Storage;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document("logreaperqueues")
public class QueueToStore {
    @Id
    private Integer queueId;
    private String topicName;
    private String queueName;
    private Set<String> logs;
}
