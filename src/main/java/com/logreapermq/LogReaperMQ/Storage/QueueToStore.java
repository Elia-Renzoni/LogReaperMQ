package com.logreapermq.LogReaperMQ.Storage;

import java.util.List;
import java.util.Set;
import com.logreapermq.LogReaperMQ.QueueSystem.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueToStore {
    private String topicName;
    private String queueName;
    private List<Set<Message>> logs;

    public QueueToStore(final String tName, final String qName, final List<Set<Message>> logs) {
        this.topicName = tName;
        this.queueName = qName;
        this.logs = logs;
    }
}
