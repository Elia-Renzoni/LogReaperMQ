package com.logreapermq.LogReaperMQ.QueueSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    private String message;
    private Boolean broadcastSession;
}
