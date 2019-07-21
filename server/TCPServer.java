import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer{
	private static ServerSocket socket;
	private static final int PORT = 5000;
	
	public static void main(String[] args){
		System.out.println("Opening port...\n");
		try{
			socket = new ServerSocket(PORT);
		}catch(IOException exception){
			System.out.println("Unable...");
			System.exit(1);
		}
		do{
			waitClient();
		}while(true);
	}
	private static void waitClient(){
		Socket server = null;
		try{
			server = socket.accept();
			Scanner input = new Scanner(server.getInputStream());
			PrintWriter output = new PrintWriter(server.getOutputStream(), true);
			int numberMessage = 0;
			String message = input.nextLine();
			while(!message.equals("CLOSE")){
				System.out.println("Message received");
				numberMessage++;
				output.println("Message "+numberMessage+ ": "+ message);
				message = input.nextLine();
			}
			output.println(numberMessage+ " messages received.");
		}catch(IOException exception){
			exception.printStackTrace();
		}finally{
			try{
				System.out.println("\nClosing connection...");
				server.close();
			}catch(IOException e){
				System.out.println("Error");
				System.exit(1);
			}
		}
	}
}