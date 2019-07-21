import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient{
	private static InetAddress inetAddress;
	private static final int PORT = 5000;
	
	public static void main(String[] args){
		try{
			inetAddress = InetAddress.getLocalHost();
			
		}catch(UnknownHostException except){
			System.out.println("Host not found");
			System.exit(1);
		}
		accessServer();
	}
	private static void accessServer(){
		Socket socket = null;
		try{
			socket = new Socket(inetAddress, PORT);
			Scanner input = new Scanner(socket.getInputStream());
			
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			
			Scanner userEntry = new Scanner(System.in);
			
			String message, response;
			
			do{
				System.out.print("Enter message: ");
				message = userEntry.nextLine();
				output.println(message);
				response = input.nextLine();
				System.out.println("SERVER: "+response);
			}while(!message.equals("CLOSE"));
			
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				System.out.println("\nClosing connection...");
				socket.close();
				
			}catch(IOException except){
				System.out.println("error when disconnect...");
				System.exit(1);
			}
			
		}
	}
}