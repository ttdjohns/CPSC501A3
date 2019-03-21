import org.jdom2.*;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Receiver {
	private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static int port = 4444;
    private static PrintWriter out;
    private static BufferedReader in;
	
	public void deserializer() { 
		
	}
	
	public static void main() throws IOException {
		//send over socket
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	
}
