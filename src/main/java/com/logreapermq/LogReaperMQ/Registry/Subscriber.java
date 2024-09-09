package com.logreapermq.LogReaperMQ.Registry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Subscriber {
    private String host;
    private Integer port;
}
