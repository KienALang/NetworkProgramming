import com.ex02.Postfix;
import com.ex02.Server;

public class Main {
    public static void main(String arg[]) {
        Server server = new Server();
        try {
            while (true) {
                String expression = server.getMessageFromClient();
                System.out.println("Client: "+expression);

                Postfix postfix = new Postfix(expression);
                server.sendMessageToClient(postfix.getValue());
            }
        } catch (Exception e) {
            try {
                server.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }
}
