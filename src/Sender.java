import org.jdom2.*;
import java.util.Collections.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sender {
	public List<UserObject> objList = new ArrayList<UserObject>();
	static int nextID = 0;
	static Scanner s = new Scanner(System.in);
	
	public static void objCreator() {
		boolean fin = false;
		System.out.println("Select an option: \n"
				+ "   OP - Single object with primitives \n"
				+ "   OR - Single object with object references"
				+ "   AP - An object containing an Array of primitives \n"
				+ "   AO - An object containing an Array of objects "
				+ "   C - collection class");
		while (!fin) {
			String line = s.nextLine();
			if (line.equals("OP")) {
				fin = true;
				createOP();
			} 
			else if (line.equals("OR")) {
				fin = true;
				createOR();
			}
			else if (line.equals("AP")) {
				fin = true;
				createAP();
			}
			else if (line.equals("AO")) {
				fin = true;
				createAO();
			}
			else if (line.equals("C")) {
				fin = true;
				createCollection();
			}
			else {
				System.out.println("That was not a valid entry");
			}
		}
	}
	
	private static void createOP() {
		System.out.println("Give the class name:");
		String cls = s.nextLine();
		UserObject obj = new UserObject(cls, nextID, "OP");
		
	}
	private static void createOR() {
		// TODO Auto-generated method stub
		
	}
	
	private static void createAP() {
		// TODO Auto-generated method stub
		
	}
	
	private static void createAO() {
		// TODO Auto-generated method stub
		
	}

	private static void createCollection() {
		// TODO Auto-generated method stub
		
	}

	public static void serializer() {
		
	}

	public static void main(String[] args) {
		objCreator();
		serializer();
		
		//send over socket
		
	}
}
