package com.logreapermq.LogReaperMQ.QueueSystem;

public class QueueIdempotency<Integer, String> {
    private Integer uniqueItemId;
    private String itemValue;

    public QueueIdempotency(final Integer id, final String itemValue) {
        this.uniqueItemId = id;
        this.itemValue = itemValue;
    }

    public Integer getItemId() {
        return this.uniqueItemId;
    }

    public String getItemValue() {
        return this.itemValue;
    }
}
