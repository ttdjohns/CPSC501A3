import org.jdom2.*;
import org.xml.sax.SAXException;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Receiver {
	private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static int port = 4444;
    //private static PrintWriter out;
    private static BufferedReader in;
	
    public static Document recieve() throws IOException, UnknownHostException, ParserConfigurationException, SAXException {
    	//send over socket
    	serverSocket = new ServerSocket(port);
    	clientSocket = serverSocket.accept();
    	//out = new PrintWriter(clientSocket.getOutputStream(), true);
    	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	String recieved = "";
    	String readL = "";
    	while ((readL = in.readLine()) != null) {
    		recieved += readL  + "\n";
    	}
    	DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    	Document doc = (Document)db.parse(recieved);
    	return doc;
    }
	
	public static void main(String[] args) throws IOException, UnknownHostException, ParserConfigurationException, SAXException{
		Document doc = recieve();
		Deserializer ds = new Deserializer();
		ds.deserialize(doc);
		
		
	}
	
	
}
