import java.util.List;

import org.jdom2.output.XMLOutputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Sender {
	private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
 
    /*public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }*/
 
    public static void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    
	public static void main(String[] args) throws UnknownHostException, IOException {
		ObjectCreator oc = new ObjectCreator();
		oc.objCreator();
		List<UserObject> objList = oc.getObjList();
		Serializer s = new Serializer();
		org.jdom2.Document doc = s.serialize(objList);
		
		startConnection("127.0.0.1", 4444);
		out.println(new XMLOutputter().outputString(doc));
		
        
	}
}
