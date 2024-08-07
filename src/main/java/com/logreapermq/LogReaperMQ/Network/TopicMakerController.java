package com.logreapermq.LogReaperMQ.Network;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "reapermq/maker")
public class TopicMakerController {

    @PostMapping("/topic")
    public ResponseEntity<Void> createTopic() {
        return null;
    }

    @PostMapping("/subtopic")
    public ResponseEntity<Void> createSubtopic() {
        return null;
    }
    
}
