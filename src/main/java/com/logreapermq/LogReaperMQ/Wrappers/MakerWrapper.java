package com.logreapermq.LogReaperMQ.Wrappers;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter 
@Setter 
@NonNull
public class MakerWrapper {
    private String topic;
    private List<String> queues;

    public MakerWrapper() {
       this.queues = new LinkedList<>(); 
    }  
}
