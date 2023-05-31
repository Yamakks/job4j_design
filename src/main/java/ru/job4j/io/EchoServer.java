package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
            try (ServerSocket server = new ServerSocket(9000)) {
                while (!server.isClosed()) {
                    Socket socket = server.accept();
                    try (OutputStream out = socket.getOutputStream();
                         BufferedReader in = new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        String s = in.readLine();
                        s = s.split(" ")[1].split("=")[1];
                        switch (s) {
                            case ("Hello") -> out.write("Hello".getBytes());
                            case ("Exit") -> server.close();
                            default -> out.write(s.getBytes());
                        }
                        for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                            System.out.println(str);
                        }
                        out.flush();
        }
                }
    } catch (IOException e) {
                LOG.error(" IO Error", e);
            }
    }
}