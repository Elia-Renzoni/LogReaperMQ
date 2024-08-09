package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchTries;

public class QueueEnvironment {
    private List<QueueIdempotency> queue;
    private String queueName;
    private Random rand;

    private static final Integer MAX_TRIES = 100;
    private static final Integer MAX_ID = 100;

    public QueueEnvironment(final String name) {
        this.queue = new LinkedList<>();
        this.queueName = name;
        this.rand = new Random();
    }

    public SystemErrorsBinder addItem(final String item) {
        // check for idempotency
        try {
            this.queue.add(new QueueIdempotency<Integer,String>(this.generateUniqueElementID(), item));
        } catch (TooMutchTries e) {
            return SystemErrorsBinder.QUEUE_TOO_HOT;
        }
        return SystemErrorsBinder.OK_STATUS;
    }

    public String getQueueName() {
        return this.queueName;
    }
    
    public void deleteItem(final String item) {
        this.queue.remove(item);
    }

    private Boolean idempotencyControl(final String item) {
        // todo, to set the json class
        return false;
    }

    private Integer generateUniqueElementID() throws TooMutchTries {
        Integer id = this.rand.nextInt(QueueEnvironment.MAX_ID);
        Integer tries = 0;
        while (true) {
            if (tries >= QueueEnvironment.MAX_TRIES) {
                throw new TooMutchTries("Overload");
            }

            final Integer tmpID = id;
            Boolean opResult = this.queue.stream()
                        .filter(n -> n.getItemId() == tmpID)
                        .anyMatch(null);
            if (!(opResult)) {
                id = this.rand.nextInt(QueueEnvironment.MAX_ID);
            } else {
                break;
            }
            tries++;
        }
        return id;
    }
}
