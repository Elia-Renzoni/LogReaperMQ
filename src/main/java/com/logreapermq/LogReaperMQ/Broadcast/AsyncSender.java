package com.logreapermq.LogReaperMQ.Broadcast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

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
        return tPool;
    }
    
    @Async("threadPoolTaskExecutor")
    public void sendToSubscribers(final List<Message> logs, final List<Subscriber> subsribers) {

    }

    public void sendToSubscriber(final Message message, final String host, final Integer port) {
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
