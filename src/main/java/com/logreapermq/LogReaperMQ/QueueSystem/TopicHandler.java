package com.logreapermq.LogReaperMQ.QueueSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchTries;

@Service
public class TopicHandler {
    private Map<QueueUniqueIdentificatorSystem, QueuesManager> mainHandler;
    private Random rand;
    private Set<Integer> idRegister;

    private static final Integer MAX_ID = 5_000;
    private static final Integer MAX_TRIES = 5_000;

    public TopicHandler() {
        this.mainHandler = new HashMap<>();
        this.rand = new Random();
        this.idRegister = new HashSet<>();
    }

    public synchronized SystemErrorsBinder addNewTopic(final String topicName) {
        Integer topicId = 0;

        try {
            topicId = this.generateRandomID();
        } catch (TooMutchTries e) {
            return SystemErrorsBinder.TOO_MUCH_ELEMENTS;
        }

        this.mainHandler.put(new QueueUniqueIdentificatorSystem<Integer, String>(topicId, topicName), 
                                new QueuesManager());
        return SystemErrorsBinder.OK_STATUS;
    }

    public synchronized SystemErrorsBinder addQueue(final String topicName, final String queueName) {
        SystemErrorsBinder opResult;
        // search for main topic
        Optional<QueuesManager> manager = this.checkTopicAndGetManager(topicName);

        if (manager.isPresent()) {
            opResult = manager.get().addQueue(queueName);
        } else {
            return SystemErrorsBinder.UNKNOWN_TOPIC;
        }
        return opResult;
    }

    public synchronized SystemErrorsBinder deleteQueue(final String topicName, final String queueName) {
        SystemErrorsBinder opResult;

        // search for the main topic
        Optional<QueuesManager> manager = this.checkTopicAndGetManager(topicName);

        if (manager.isPresent()) {
            opResult = manager.get().deleteQueue(queueName);
        } else {
            return SystemErrorsBinder.UNKNOWN_TOPIC;
        }
        return opResult;
    }

    private Integer generateRandomID() throws TooMutchTries {
        Integer id = this.rand.nextInt(TopicHandler.MAX_ID);
        Integer logicalCounter = 0;

        while (true) {
            if (logicalCounter >= TopicHandler.MAX_TRIES) {
                throw new TooMutchTries("Overload");
            }

            if (!(this.idRegister.contains(id))) {
                break;
            } else {
                id = this.rand.nextInt(TopicHandler.MAX_ID);
                logicalCounter++;
            }
        }
        return id;
    }

    private Optional<QueuesManager> checkTopicAndGetManager(final String topicNameToSearch) {
        return this.mainHandler.entrySet().stream()
                .filter(kv -> kv.getKey().getQueuesTopicRegister().equals(topicNameToSearch))
                .map(Map.Entry::getValue)
                .findFirst();
    }
}
