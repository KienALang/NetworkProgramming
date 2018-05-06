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

	private String getInputString() {
		return scanner.nextLine();
	}

	private boolean isQuit(String message) {
		return message.equals("quit");
	}

	private void sendMessageToServer(String message) throws Exception {
		this.outputStream.writeUTF(message);
		this.outputStream.flush();
	}

	private String getMessageFromServer() throws Exception {
		return this.inputStream.readUTF();
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();

		while (true) {
			// Enter the string
			System.out.print("Client: ");
			String message = client.getInputString();

			// Checking if it's "quit"	=> quit
			if (client.isQuit(message)) {
				client.close();
				break;
			}

			// Send message to Server
			client.sendMessageToServer(message);
			System.out.println("Server: "+client.getMessageFromServer());
		}
	}

}
