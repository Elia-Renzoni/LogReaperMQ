package com.logreapermq.LogReaperMQ.Network;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "reapermq/breaker")
public class TopicReaperController {

    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Integer topicId) {
        return null;
    }

    @DeleteMapping("/{topicId}/{subtopicId}")
    public ResponseEntity<Void> deleteSubtopic(@PathVariable Integer topicId, @PathVariable Integer subtopicId) {
        return null;
    }
}
