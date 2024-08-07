package com.logreapermq.LogReaperMQ.Network;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "reapermq/updater")
public class TopicUpdaterController {

    @PatchMapping("/topic")
    public ResponseEntity<Void> updateTopic() {
        return null;
    }

    @PatchMapping("/subtopic")
    public ResponseEntity<Void> updateSubtopic() {
        return null;
    }
    
}
