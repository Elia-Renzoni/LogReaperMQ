package com.logreapermq.LogReaperMQ.Storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.logreapermq.LogReaperMQ.QueueSystem.Message;

@Service
public class FileWrapper {
   private FileWriter store;

   public FileWrapper() {
      try {
         this.store = new FileWriter("queues.txt");
      } catch (IOException e) {
         e.printStackTrace();
      } 
   }

   public synchronized void storeQueue(final String topicName, final String queueName, final List<Set<Message>> queue) {
      String serSystem = "%s - %s \n %s";
      String.format(serSystem, topicName, queueName, queue);

      try {
         this.store.write(serSystem);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
