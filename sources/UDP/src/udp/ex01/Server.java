package udp.ex01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    private static int PORT = 2018;
    private DatagramSocket serverSocket;
    private byte[] data = new byte[10000];
    private DatagramPacket receivedPacket;
    private DatagramPacket sendPacket;

    public Server() {
        try {
            serverSocket = new DatagramSocket(PORT);
            receivedPacket = new DatagramPacket(data, data.length);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        Server server = new Server();
        while (true) {
            String msg = server.receivedPacketString();
            System.out.println("Client: " + msg);
            String msgToSend = server.prepareMessageToSend(msg);

            server.sendData(msgToSend.getBytes());
        }
    }

    public String receivedPacketString() {
        String msg = null;
        try {
            serverSocket.receive(receivedPacket);
            msg = new String(receivedPacket.getData());
        } catch (IOException e) {
            e.printStackTrace();
            serverSocket.close();
            System.exit(1);
        }

        return msg;
    }

    public String prepareMessageToSend(String receivedMsg) {
        String msg = "UPPER CASE: " + StringHelper.toUpperCase(receivedMsg);
        msg += "\nLower case: " + StringHelper.toLowerCase(receivedMsg);
        msg += "\nWord number: " + StringHelper.countWordNum(receivedMsg);
        return msg;
    }

    public void sendData(byte[] data) {
        sendPacket = new DatagramPacket(data, data.length, receivedPacket.getAddress(), receivedPacket.getPort());
        try {
            serverSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
            serverSocket.close();
            System.exit(1);
        }
    }
}
