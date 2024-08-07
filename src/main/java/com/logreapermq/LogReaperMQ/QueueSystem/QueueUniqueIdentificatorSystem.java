package com.logreapermq.LogReaperMQ.QueueSystem;

public class QueueUniqueIdentificatorSystem<Integer, QueuesTopicRegister> {
    private Integer mainTopicId;
    private QueuesTopicRegister topicRegistry;

    public QueueUniqueIdentificatorSystem(final Integer id, final QueuesTopicRegister topics) {
        this.mainTopicId = id;
        this.topicRegistry = topics;
    }

    public Integer getMainTopicID() {
        return this.mainTopicId;
    }

    public QueuesTopicRegister getQueuesTopicRegister() {
        return this.topicRegistry;
    }
}
