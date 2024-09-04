package com.logreapermq.LogReaperMQ.QueueSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Class representing the structure of the messages
// stored in the queues.

@Getter
@Setter
@AllArgsConstructor
public class Message {
    
    // Log information to store
    private String message;
    
    // flag value that helps the broadcast trheads to 
    // indentify when a message is ready to be sent or not.
    // so when a message is broadcasted the flag become false
    private Boolean broadcastSession;
}
