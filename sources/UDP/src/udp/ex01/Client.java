package udp.ex01;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static int PORT = 2018;
    private byte[] data = new byte[50000];
    private InetAddress address;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private DatagramSocket clientSocket;

    public Client() {

        try {
            address = InetAddress.getLocalHost();
            clientSocket = new DatagramSocket();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        Client client = new Client();
        while (true) {
            String msg = client.getClientInputMsg();
            client.sendDataToServer(msg.getBytes());
            client.receivePacket();
        }
    }

    public String getClientInputMsg() {
        String msg = null;
        System.out.print("Client: ");
        Scanner scanner = new Scanner(System.in);
        msg = scanner.nextLine();
        if (msg.equalsIgnoreCase("quit")) {
            clientSocket.close();
            System.exit(1);
        }
        return msg;
    }

    public void sendDataToServer(byte[] data) {
        sendPacket = new DatagramPacket(data, data.length, address, PORT);
        try {
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receivePacket() {
        receivePacket = new DatagramPacket(data, data.length);
        try {
            clientSocket.receive(receivePacket);
            String modifiedStr = new String(receivePacket.getData());
            System.out.println(modifiedStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
