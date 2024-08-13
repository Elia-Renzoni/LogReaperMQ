package com.logreapermq.LogReaperMQ.QueueSystem;

public class QueueUniqueIdentificatorSystem<E> {
    private E topicID;

    public QueueUniqueIdentificatorSystem(final E id) {
        this.topicID = id;
    }

    public E getMainTopicID() {
        return this.topicID;
    }
}
