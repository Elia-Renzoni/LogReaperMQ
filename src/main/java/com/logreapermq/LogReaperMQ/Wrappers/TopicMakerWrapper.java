package com.logreapermq.LogReaperMQ.Wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter 
@Setter 
@NonNull
@AllArgsConstructor
public class TopicMakerWrapper {
    private String topic;
}
