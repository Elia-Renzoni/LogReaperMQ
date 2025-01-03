package com.logreapermq.LogReaperMQ.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LogReaperMQSubscriberDemo {
    public static void main(String... args) {
        // listening and printing logs
        ServerSocket conn = null;
        try {
            conn = new ServerSocket(5600);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            System.out.println("Listening...");
            while (true) {
                Socket net = conn.accept();
                System.out.println(net.getInputStream().read());
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}