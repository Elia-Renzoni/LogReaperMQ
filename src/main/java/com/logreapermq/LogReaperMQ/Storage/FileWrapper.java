package com.logreapermq.LogReaperMQ.Storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.logreapermq.LogReaperMQ.QueueSystem.Message;

@Component
public class FileWrapper {
   private FileWriter store;
   private static final Logger logger = LoggerFactory.getLogger(FileWrapper.class);


   public FileWrapper() {
      try {
         this.store = new FileWriter("queues.txt", true);
      } catch (IOException e) {
         e.printStackTrace();
      } 
   }

   public void storeQueue(final String topicName, final String queueName, final Set<Message> queue) {
      String serSystem = "%s - %s - %s\n";
      String messages = null;
      String result;

      for (var msg : queue) {
         messages += msg.getMessage() + "\t";
      }

      result = String.format(serSystem, topicName, queueName, messages);
      FileWrapper.logger.info(result);

      try {
         this.store.write(result);
         this.store.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
