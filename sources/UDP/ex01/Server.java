import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
	
	public static void main(String[] args) {
		System.out.println("Host is running");
		try {
			DatagramPacket dp = new DatagramPacket(new byte[256], 256);
			DatagramSocket ds = new DatagramSocket(1254);
			ds.receive(dp);
			byte data[] = dp.getData();
			for(byte b : data) {
				System.out.print((char)b);
			}
			
			int senderPort = dp.getPort();
			InetAddress senderAddress = dp.getAddress();
			String msg = "I got your message!";
			
			byte[] dataToSend = msg.getBytes();
			dp.setData(dataToSend);
			dp.setAddress(senderAddress);
			dp.setLength(dataToSend.length);
			
			ds.send(dp);
			
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Host is closed!");
		
	}
}