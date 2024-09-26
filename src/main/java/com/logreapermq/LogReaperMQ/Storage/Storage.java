package com.logreapermq.LogReaperMQ.Storage;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/*
 * TODO:
 * Aaggiungere metodo di scheduling per prendere
 * le code con dimensione massima raggiunta e memorizzarle
 * su un database mongoDB.
 * una volta memorizzate posso eliminare gli elementi 
 * vecchi nella coda.
 */
@EntityScan
@EnableScheduling
public class Storage {

    @Scheduled(fixedRate = 1000000)
    public void storeQueue() {

    }
    
}
