import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static final int PORT = 1;
	private static final String HOST_NAME = "localhost";
	private Socket clientSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Scanner scanner;
	public static String mathInputString = "0123456789.+-*/()";

	public boolean isInputStringCorrect(String str) {
		for (char c : str.toCharArray()) {
			if (!mathInputString.contains(String.valueOf(c))) {
				return false;
			}
		}

		return true;
	}

	public Client() {
		scanner = new Scanner(System.in);
		try {
			this.clientSocket = new Socket(HOST_NAME, PORT);
			this.inputStream = new DataInputStream(this.clientSocket.getInputStream());
			this.outputStream = new DataOutputStream(this.clientSocket.getOutputStream());
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
	}

	public void close() throws Exception {
		this.clientSocket.close();
		this.scanner.close();
	}

	public String getInputString() {
		return scanner.nextLine();
	}

	public boolean isQuit(String message) {
		return message.equals("quit");
	}

	public void sendMessageToServer(String message) throws Exception {
		this.outputStream.writeUTF(message);
		this.outputStream.flush();
	}

	public String getMessageFromServer() throws Exception {
		return this.inputStream.readUTF();
	}



	public static void main(String[] args) {
		Client client = new Client();

		try {
			while (true) {
				String expression;
				System.out.print("Client: ");
				expression = client.getInputString();

				if (client.isQuit(expression)) {
					client.close();
					break;
				}

				if (client.isInputStringCorrect(expression)) {
					client.sendMessageToServer(expression);
					System.out.println("Server: " + client.getMessageFromServer());
				}else {
					System.out.println("Expression you've entered is incorrect! Please enter again.");
					continue;
				}

			}
		} catch (Exception e) {
			System.out.println("Errors: " + e.getMessage());
		}

	}
}
