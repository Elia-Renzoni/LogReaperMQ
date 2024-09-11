package com.logreapermq.LogReaperMQ.Broadcast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.logreapermq.LogReaperMQ.QueueSystem.Message;
import com.logreapermq.LogReaperMQ.Registry.Subscriber;

// Best-Effort Broadcast Implementation for LogReaperMQ
// the algorithm execut apart of the main thread pool
@EnableAsync
public class AsyncSender {
   
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor tPool = new ThreadPoolTaskExecutor();
        tPool.setMaxPoolSize(10);
        tPool.setQueueCapacity(100);
        tPool.setCorePoolSize(10);
        tPool.initialize();
        return tPool;
    }
    
    @Async("threadPoolTaskExecutor")
    public void sendToSubscribers(final Set<Message> queue, final Set<Subscriber> subsribers) {
        for (Message log : queue) {
            if (log.getBroadcastSession()) {
                for (Subscriber subInfo : subsribers) {
                    this.sendTo(log, subInfo.getHost(), subInfo.getPort());
                }
                log.setBroadcastSession(false);
            }
        }
    }

    public void sendTo(final Message message, final String host, final Integer port) {
        Socket conn;
        try {
            conn = new Socket(host, port);
            var writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(message.getMessage());
            writer.flush();

            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
