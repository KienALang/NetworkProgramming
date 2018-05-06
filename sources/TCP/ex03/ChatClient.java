import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            final Socket soc = new Socket("localhost", 9999);
            String clientName;
            System.out.print("Please Enter your name: ");
            Scanner scanner = new Scanner(System.in);
            clientName = scanner.nextLine();

            while (true) {

                new Thread(new Runnable() {
                    Scanner sc = new Scanner(System.in);
                    String msg = sc.nextLine();

                    @Override
                    public void run() {
                        try {
                            if (msg.equalsIgnoreCase("bye")) {
                                System.exit(1);
                            }

                            msg = clientName + ": " + msg;
                            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
                            out.println(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Scanner in = new Scanner(soc.getInputStream());
                            while (in.hasNextLine()) {
                                System.out.println(in.nextLine());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}