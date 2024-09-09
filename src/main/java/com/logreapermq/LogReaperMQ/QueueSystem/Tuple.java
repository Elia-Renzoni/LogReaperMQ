package com.logreapermq.LogReaperMQ.QueueSystem;

import lombok.*;

@Getter
public class Tuple<Boolean, QueueEnvironment> {
    private Boolean opResult;
    private QueueEnvironment associateQueue;

    public Tuple(final Boolean op, final QueueEnvironment queue) {
        this.opResult = op;
        this.associateQueue = queue;
    }
}
