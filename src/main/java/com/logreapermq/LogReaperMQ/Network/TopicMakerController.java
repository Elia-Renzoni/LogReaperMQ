package com.logreapermq.LogReaperMQ.Network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logreapermq.LogReaperMQ.QueueSystem.TopicHandler;
import com.logreapermq.LogReaperMQ.Wrappers.MakerWrapper;

@RestController
@RequestMapping(path = "reapermq/maker")
public class TopicMakerController {
    @Autowired
    private TopicHandler queueHolder;

    @PostMapping("/topic")
    public ResponseEntity<Void> createTopic(@RequestBody MakerWrapper requestBody) {
        // devo controllase so sono null o meno, se sono null allora devo inviare una risposta negativa, altrimenti una positiva
        // chiamo il metodo per aggiungere un topic, controllo se ci sono errori e invio di conseguenza rispote negative o positive.
         
        return null;
    }

    @PostMapping("/queue")
    public ResponseEntity<Void> createSubtopic() {
        return null;
    }
    
}
