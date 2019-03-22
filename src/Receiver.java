import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import jdk.internal.org.xml.sax.InputSource;

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
import java.io.StringReader;

public class Receiver {
	private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static int port = 4444;
    //private static PrintWriter out;
    private static BufferedReader in;
	
    public static void setup() throws IOException {
    	serverSocket = new ServerSocket(port);
    }
    
    public static Document recieve() throws IOException, UnknownHostException, ParserConfigurationException, SAXException {
    	//send over socket
    	
    	clientSocket = serverSocket.accept();
    	//out = new PrintWriter(clientSocket.getOutputStream(), true);
    	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	String recieved = "";
    	String readL = "";
    	while ((readL = in.readLine()) != null) {
    		recieved += readL;
    	}
    	org.jdom2.input.SAXBuilder saxBuilder = new SAXBuilder();
    	org.jdom2.Document doc = null;
    	try {
    		 doc = saxBuilder.build(new StringReader(recieved));
    	} catch (Exception e) {
    		System.out.println(e);
    		System.exit(-1);
    	}
    	return doc;
    }
	
	public static void main(String[] args) throws IOException, UnknownHostException, ParserConfigurationException, SAXException{
		setup();
		while (true) {
			Document doc = recieve();
			Deserializer ds = new Deserializer();
			ds.deserialize(doc);
			Visualizer v = new Visualizer();
			v.display(ds.objList);
		}
	}
	
	
}
