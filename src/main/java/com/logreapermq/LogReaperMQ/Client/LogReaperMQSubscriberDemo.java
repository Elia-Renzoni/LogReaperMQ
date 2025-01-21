package com.logreapermq.LogReaperMQ.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LogReaperMQSubscriberDemo {
    public static void main(String... args) {
        // listening and printing logs
        ServerSocket conn = null;
        try {
            conn = new ServerSocket(5600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Listening...");
            while (true) {
                Socket net = conn.accept();
                var in = new BufferedReader(new InputStreamReader(net.getInputStream()));
                System.out.println(in.readLine());
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}