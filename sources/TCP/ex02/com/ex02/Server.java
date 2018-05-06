package com.ex02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int PORT = 1;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Scanner scanner;

    public Server() {
        scanner = new Scanner(System.in);
        try {
            this.serverSocket = new ServerSocket(PORT);
            this.clientSocket = this.serverSocket.accept();
            System.out.println("Accepted the request connection from client");
            this.inputStream = new DataInputStream(this.clientSocket.getInputStream());
            this.outputStream = new DataOutputStream(this.clientSocket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void close() throws Exception {
        this.serverSocket.close();
        this.scanner.close();
    }

    public String getMessageFromClient() throws Exception {
        return this.inputStream.readUTF();
    }

    public void sendMessageToClient(String message) throws Exception {
        this.outputStream.writeUTF(message);
        this.outputStream.flush();
    }
}
