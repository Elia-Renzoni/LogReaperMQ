package com.logreapermq.LogReaperMQ.Wrappers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ack {
    private String responseMessage;
}
