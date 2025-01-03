package com.logreapermq.LogReaperMQ.Storage;

import java.util.concurrent.Executor;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableAsync
public class AsyncStorage {
    private QueueToStore serQueue;
    @Autowired
    private FileWrapper store;

    @Bean(name = "threadPoolTaskExecutorStorage")
    public Executor storageTaskExecutor() {
        ThreadPoolTaskExecutor tPool = new ThreadPoolTaskExecutor();
        tPool.setMaxPoolSize(101);
        tPool.setQueueCapacity(101);
        tPool.setCorePoolSize(101);
        return tPool;
    }

    /*
     */
    @Async("threadPoolTaskExecutorStorage")
    public void storeAndDelete(final List<ManagersPairStructure<QueuesManager, String>> managers) {
        for (var toDelete : managers) {
            for (var queues : toDelete.getManager().getTopicQueues()) {
                this.serQueue = new QueueToStore(toDelete.getTopicOfManager(), queues.getQueueName(), List.of(queues.getMessageQueue()));
                this.store.storeQueue(this.serQueue.getTopicName(), this.serQueue.getQueueName(), this.serQueue.getLogs());
                queues.deleteItems();
            }
        }
    }
}
