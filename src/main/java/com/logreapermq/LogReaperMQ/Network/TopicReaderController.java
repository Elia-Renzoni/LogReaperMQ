package com.logreapermq.LogReaperMQ.Network;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "reapermq/subscribers")
public class TopicReaderController {

    @GetMapping("/{topicId}")
    public ResponseEntity<Void> incomingMessagesFromTopic(@PathVariable Integer topicId) {
        return null;
    }

    @GetMapping("/{topicId}/{subtopicId}")
    public ResponseEntity<Void> incomingMessagesFromSubtopic(@PathVariable Integer topicId, @PathVariable Integer subtopicId) {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerToTopic() {
        return null;
    }
    
}
