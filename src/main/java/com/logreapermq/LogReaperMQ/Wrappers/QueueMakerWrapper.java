package com.logreapermq.LogReaperMQ.Wrappers;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QueueMakerWrapper {
    private String topic;
    private String queue;
}
