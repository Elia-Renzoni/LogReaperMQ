package com.logreapermq.LogReaperMQ.Storage;

import java.util.concurrent.Executor;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;

@Configuration
@EnableAsync
public class AsyncStorage {
    private QueueToStore queueDocumentTemplate;

    @Bean(name = "threadPoolTaskExecutorStorage")
    public Executor storageTaskExecutor() {
        ThreadPoolTaskExecutor tPool = new ThreadPoolTaskExecutor();
        tPool.setMaxPoolSize(101);
        tPool.setQueueCapacity(101);
        tPool.setCorePoolSize(101);
        return tPool;
    }

    /*
     * devo memorizzare le code con le seguenti informazioni:
     * - id coda.
     * - nome del topic di riferimento.
     * - nome della coda.
     * - logs.
     */
    @Async("threadPoolTaskExecutorStorage")
    public void storeAndDelete(final List<ManagersPairStructure<QueuesManager, String>> managers) {
        for (var toDelete : managers) {
            for (var queues : toDelete.getManager().getTopicQueues()) {
                this.queueDocumentTemplate = new QueueToStore(2,  
                                                    toDelete.getTopicOfManager(), 
                                                            queues.getQueueName(), 
                                                                List.of(queues.getMessageQueue()));
                queues.deleteItems();
            }
        }
    }
}
