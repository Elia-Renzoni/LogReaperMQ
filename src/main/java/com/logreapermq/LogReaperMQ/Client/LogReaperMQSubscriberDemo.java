package com.logreapermq.LogReaperMQ.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LogReaperMQSubscriberDemo {
    public static void main(String... args) {
        // listening and printing logs
        private ServerSocket conn = new ServerSocket(5600);
        try {
            while (true) {
                Socket net = conn.accept();
                System.out.println(net.getInputStream().read());
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}