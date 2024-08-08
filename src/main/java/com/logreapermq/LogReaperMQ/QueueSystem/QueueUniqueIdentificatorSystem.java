package com.logreapermq.LogReaperMQ.QueueSystem;

public class QueueUniqueIdentificatorSystem<Integer, String> {
    private Integer mainTopicId;
    private String mainTopicName;

    public QueueUniqueIdentificatorSystem(final Integer id, final String topic) {
        this.mainTopicId = id;
        this.mainTopicName = topic;
    }

    public Integer getMainTopicID() {
        return this.mainTopicId;
    }

    public String getQueuesTopicRegister() {
        return this.mainTopicName;
    }
}
