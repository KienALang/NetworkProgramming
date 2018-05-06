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

	private boolean isQuit(String message) {
		return message.equals("quit");
	}

	private String getInputString() {
		return scanner.nextLine();
	}

	private void sendMessageToClient(String message) throws Exception {
		this.outputStream.writeUTF(message);
		this.outputStream.flush();
	}

	private String getMessageFromClient() throws Exception {
		return this.inputStream.readUTF();
	}

	public String msgToUpperCase(String msg) {
		return msg.toUpperCase();
	}

	public String msgToLowerCase(String msg) {
		return msg.toLowerCase();
	}

	public int countWordNum(String msg) {
		char array[] = msg.toCharArray();
		String word = "";
		int count = 0;

		for (char c : array) {
			if(c != ' ') {
				word += c;
			}else if(word != "") {
				++count;
				word = "";
			}
		}

		if (word != "") {
			++count;
		}


		return count;
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server();

		try {
			while (true) {
				String clientMessage = server.getMessageFromClient();
				System.out.println("Client: "+clientMessage);
				String responseMessage = "Here is what you've requested: \n";
				responseMessage += "lowercase: "+server.msgToLowerCase(clientMessage) + "\n";
				responseMessage += "UPPERCASE: "+server.msgToUpperCase(clientMessage) + "\n";
				responseMessage += "Word num: "+server.countWordNum(clientMessage) + "\n";

				server.sendMessageToClient(responseMessage);
			}

		} catch (Exception e) {
			server.close();
		}
		
	}

}