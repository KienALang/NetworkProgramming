import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int PORT = 7000;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Scanner scanner;
    private int[] number;

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

    private void sendMessageToClient(String message) throws Exception {
        this.outputStream.writeUTF(message);
        this.outputStream.flush();
    }

    private String getMessageFromClient() throws Exception {
        return this.inputStream.readUTF();
    }

    private void setNumber(String msg) {
        String[] data = msg.split(",");
        int i = 0;
        number = new int[data.length];
        for (String num: data) {
            number[i++] = Integer.parseInt(num);
        }
    }

    private int countLastZeroNumber() {
        int count = 0;
        double t = 1;
        if (number.length == 2) {
            for (int i = number[0]; i <= number[1]; i++) {
                t *= i;
                while (t > 0 && t % 10 == 0) {
                    ++count;
                    t = t / 10;
                }
            }
        }else {
            if (number.length > 2) {
                for (int i = 0; i < number.length; i++) {
                    t *= number[i];
                    while (t > 0 && t % 10 == 0) {
                        ++count;
                        System.out.println(count);
                        t = t / 10;
                    }
                }
            }
        }

        return  count;
    }



    public static void main(String[] args) throws Exception {
        Server server = new Server();

        try {
            while (true) {
                String clientMessage = server.getMessageFromClient();
                System.out.println("Client: "+clientMessage);
                server.setNumber(clientMessage.trim());
                String msg = ""+ server.countLastZeroNumber();
                server.sendMessageToClient(msg);
            }

        } catch (Exception e) {
            server.close();
        }

    }

}