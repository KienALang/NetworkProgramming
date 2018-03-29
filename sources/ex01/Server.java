import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static ServerSocket serverSocket;
	
	public static void main(String[] args) {
		
		try {
			serverSocket = new ServerSocket(1234);
			Socket socket = serverSocket.accept();
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			while(true) {
				String msg = dis.readUTF();
				System.out.println("Client: "+msg);

				if (msg.equals("exit")) {
					serverSocket.close();
					break;
				}
				
				dos.writeUTF("\nUpper Case: "+msgToUpperCase(msg) + "\nLower Case: "+msgToLowerCase(msg)+"\nNumber of word:"+countWordNum(msg)+"\n");
				dos.flush();
			}

		} catch (Exception e) {
			System.out.println("Errors: "+e.getMessage());
		}
		
		
	}
	
	public static String msgToUpperCase(String msg) {
		return msg.toUpperCase();
	}
	
	public static String msgToLowerCase(String msg) {
		return msg.toLowerCase();
	}
	
	public static int countWordNum(String msg) {
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
}