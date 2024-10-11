package com.logreapermq.LogReaperMQ.Storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ManagersPairStructure<QueuesManager, String>{
    private QueuesManager manager;
    private String topicOfManager;
}
