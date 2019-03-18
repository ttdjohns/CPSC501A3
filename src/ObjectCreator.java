import java.util.Collections.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjectCreator {

	public List<UserObject> objList = new ArrayList<UserObject>();
	int nextID = 0;
	Scanner s = new Scanner(System.in);
	
	public void objCreator() {
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
	
	private void createOP() {
		System.out.println("Give the class name:");
		String cls = s.nextLine();
		UserObject obj = new UserObject(cls, nextID, "OP");
		
	}
	private void createOR() {
		// TODO Auto-generated method stub
		
	}
	
	private void createAP() {
		// TODO Auto-generated method stub
		
	}
	
	private void createAO() {
		// TODO Auto-generated method stub
		
	}

	private void createCollection() {
		// TODO Auto-generated method stub
		
	}
}
