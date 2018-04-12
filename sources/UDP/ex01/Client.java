import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	public static void main(String[] args) {
		System.out.println("Client is running!");
		String data = "Hello World";
		byte b[] = data.getBytes();
				
		try {
			DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getLocalHost(), 1254);
			DatagramSocket ds = new DatagramSocket();
			// Send data
			ds.send(dp);
			
			
			DatagramPacket receivedPacket = new DatagramPacket(new byte[256], 256);
			ds.receive(receivedPacket);
			byte receivedData[] = receivedPacket.getData();
			for(byte mb : receivedData) {
				System.out.print((char)mb);
			}
			
			System.out.println("Client is closed!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Client is closed!");
	}
}
