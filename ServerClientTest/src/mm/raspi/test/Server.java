package mm.raspi.test;

//Server Side
import java.net.*;
import java.io.*;

public class Server {

	public void run() throws IOException {
		ServerSocket serverSocket = null;
		try {
			int serverPort = 4020;
			serverSocket = new ServerSocket(serverPort);
			serverSocket.setSoTimeout(10000); 
			while(true) {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..."); 

				Socket server = serverSocket.accept();
				System.out.println("Just connected to " + server.getRemoteSocketAddress()); 

				PrintWriter toClient = 
					new PrintWriter(server.getOutputStream(),true);
				BufferedReader fromClient =
					new BufferedReader(
							new InputStreamReader(server.getInputStream()));
				String line = fromClient.readLine();
				System.out.println("Server received: " + line); 
				toClient.println("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!"); 
			}
		}
		catch(UnknownHostException ex) {
			ex.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			serverSocket.close();
		}
	  }
	
		
	  public static void main(String[] args) {
			Server srv = new Server();
			try {
				srv.run();
			} catch (IOException e) {
				e.printStackTrace();
			}
	  }
}
