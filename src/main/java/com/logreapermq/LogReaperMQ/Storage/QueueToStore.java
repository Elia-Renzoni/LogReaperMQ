package com.logreapermq.LogReaperMQ.Storage;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.logreapermq.LogReaperMQ.QueueSystem.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("logreaperqueues")
public class QueueToStore {
    @Id
    private Integer queueId;
    private String topicName;
    private String queueName;
    private List<Set<Message>> logs;

    public QueueToStore(final String tName, final String qName, final List<Set<Message>> logs) {
        this.topicName = tName;
        this.queueName = qName;
        this.logs = logs;
    }
}
