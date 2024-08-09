package com.logreapermq.LogReaperMQ.RegistrySystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;

@Service
public class RegistrationHandler {
   private Map<Subscriber, List<String>> subscribers;
   
   public RegistrationHandler() {
        this.subscribers = new HashMap<>();
   }

   public SystemErrorsBinder addSubscriber(final String topic, final List<String> subtopics) {
        return null;
   }
   
}
