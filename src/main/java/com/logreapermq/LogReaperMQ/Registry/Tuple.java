package com.logreapermq.LogReaperMQ.Registry;

import java.util.Optional;

public class Tuple<SystemErrorsBinder, Integer> {
    private SystemErrorsBinder opResult;
    private Optional<Integer> id;

    public Tuple(final SystemErrorsBinder op, final Integer id) {
        this.opResult = op;
        if (id == null) 
            this.id = Optional.empty();
        else
            this.id = Optional.of(id);
    }

    public SystemErrorsBinder getOpResult() {
        return this.opResult;
    }

    public Integer getID() {
        return this.id.get();
    }
}
