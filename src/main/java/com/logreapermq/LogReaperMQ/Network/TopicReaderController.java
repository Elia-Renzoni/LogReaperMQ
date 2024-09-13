package com.logreapermq.LogReaperMQ.Network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logreapermq.LogReaperMQ.Registry.SubRegistry;
import com.logreapermq.LogReaperMQ.Security.SystemErrorsBinder;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.TooMutchTries;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownItem;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownQueue;
import com.logreapermq.LogReaperMQ.Security.SystemExceptions.UnknownTopic;
import com.logreapermq.LogReaperMQ.Wrappers.SubscriberRegistryWrapper;

import jakarta.validation.Valid;

// this RestController handle and support
// the subscriber operations.
// those operations included the registration and
// the de-registration of the callback methods from
// topics and queues.
// If a subscriber want to de-registrate him self
// from all the queues he better "call" deregisterFromTopic
// method, otherwise he can call deregisterFromQueue.
// Each subsriber that will call the method foo will register 
// to a single topic and to the many queues of that topic.

@RestController
@RequestMapping(path = "reapermq/subscribers", produces = "application/json")
public class TopicReaderController {

    @Autowired
    private SubRegistry registrer;

    // registration of the subcribers callback method
    @PostMapping("/register")
    public ResponseEntity<Object> registerCallBackMethod(@Valid @RequestBody SubscriberRegistryWrapper registry) throws RuntimeException {
        SystemErrorsBinder op = registrer.entry(registry.getSubscriberHost(), registry.getSubscriberListenPort(), registry.getTopics(), registry.getQueues());
        if (op == SystemErrorsBinder.UNKNOWN_TOPIC) {
            throw new UnknownTopic("Unknown Topic!");
        } else if (op == SystemErrorsBinder.UNKNOWN_QUEUE) {
            throw new UnknownQueue("Unknown Queue");
        } else if (op == SystemErrorsBinder.TOO_MUTCH_TRIES) {
            throw new TooMutchTries("Too Mutch Tries to Registry a new Subscriber!");
        }
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body("CallBack Method Added");
    }
    

    // de-registration of the subscriber form a specific queue
    @DeleteMapping("/deregister/{id}/{port}/{topic}/{queue}")
    public ResponseEntity<Void> deregisterFromQueue(@PathVariable(name = "id") String id, 
                                                    @PathVariable(name = "port") Integer port, 
                                                    @PathVariable(name = "topic") String topic, 
                                                    @PathVariable(name = "queue") String queue) throws RuntimeException {
        SystemErrorsBinder op = registrer.deleteEntry(id, port, topic, queue);
        if (op == SystemErrorsBinder.UNKNOWN_ITEM) {
            throw new UnknownItem("Unknown Subscriber!");
        } else if (op == SystemErrorsBinder.UNKNOWN_QUEUE) {
            throw new UnknownQueue("The Queue " + queue + " is unknowned to the system!");
        }
        return ResponseEntity.noContent().build();
    }
}
