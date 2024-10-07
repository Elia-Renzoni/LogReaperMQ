package com.logreapermq.LogReaperMQ.Broadcast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.logreapermq.LogReaperMQ.QueueSystem.Message;
import com.logreapermq.LogReaperMQ.QueueSystem.QueueEnvironment;
import com.logreapermq.LogReaperMQ.QueueSystem.QueuesManager;
import com.logreapermq.LogReaperMQ.Registry.Subscriber;

// Best-Effort Broadcast Implementation for LogReaperMQ
// the algorithm execut apart of the main thread pool
@Configuration
@EnableAsync
public class AsyncSender {
   
    @Bean(name = "threadPoolTaskExecutorBroadcast")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor tPool = new ThreadPoolTaskExecutor();
        tPool.setMaxPoolSize(101);
        tPool.setQueueCapacity(101);
        tPool.setCorePoolSize(101);
        tPool.initialize();
        return tPool;
    }
    
    @Async("threadPoolTaskExecutorBroadcast")
    public void sendToSubscribers(final List<QueuesManager> managers) {
        for (QueuesManager manager : managers) {
            for (QueueEnvironment qEnv : manager.getTopicQueues()) {
                for (Message msg : qEnv.getMessageQueue()) {
                    if (msg.getBroadcastSession()) {
                        for (Subscriber sub : qEnv.getSubscriberHostAndPorts()) {
                            this.sendTo(msg, sub.getHost(), sub.getPort());
                        }
                    }
                }
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
