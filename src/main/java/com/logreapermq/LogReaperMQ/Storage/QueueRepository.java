package com.logreapermq.LogReaperMQ.Storage;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QueueRepository extends MongoRepository<QueueToStore, Integer> {
}