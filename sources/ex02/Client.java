import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private static Scanner sc;
	private static Socket socket;
	public static String mathInputString = "0123456789.+-*/()";

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		try {
			socket = new Socket("localhost", 1234);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			while (true) {
				String msg;
				System.out.print("Client: ");
				msg = sc.nextLine();
				
				if (msg.equals("quit")) {
					sc.close();
					socket.close();
					break;
				}

				if (isInputStringCorrect(msg)) {
					dos.writeUTF(msg);
					dos.flush();
					String serverMsg = dis.readUTF();				
					System.out.print("Server: " + serverMsg);
					sc.reset();
				}else {
					System.out.println("Expression you've entered is incorrect! Please enter again.");
					continue;
				}
						
			}
		} catch (Exception e) {
			System.out.println("Errors: " + e.getMessage());

		}

	}

	public static boolean isInputStringCorrect(String str) {
		for (char c : str.toCharArray()) {
			if (!mathInputString.contains(String.valueOf(c))) {
				return false;
			}
		}

		return true;
	}
}
