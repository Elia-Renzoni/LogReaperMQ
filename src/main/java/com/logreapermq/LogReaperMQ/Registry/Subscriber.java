package com.logreapermq.LogReaperMQ.Registry;

import java.util.List;
import java.util.Random;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchTries;

public class Subscriber {
    private String hostInfo;
    private Integer uniqueID;
    private List<Integer> ids; 
    private Random rand;

    private static final Integer MAX_SUBSCRIBERS = 1_000;

    public Subscriber(final String hostInfo) throws TooMutchTries {
        this.hostInfo = hostInfo;
        this.rand = new Random();
        this.createUniqueID();
    }

    public Integer getId() {
        return this.uniqueID;
    }

    private void createUniqueID() throws TooMutchTries {
        Integer randID = this.rand.nextInt(Subscriber.MAX_SUBSCRIBERS);
        Integer opCounter = 0;

        while (true) {
            ++opCounter;
            if (this.ids.contains(randID)) {
                randID = this.rand.nextInt(Subscriber.MAX_SUBSCRIBERS);
            } else {
                this.uniqueID = randID;
                this.ids.add(this.uniqueID);
                break;
            }

            if (opCounter > Subscriber.MAX_SUBSCRIBERS) {
                throw new TooMutchTries("Too Mutch Dude!");
            }
        }
        this.ids.clear();
    }
}
