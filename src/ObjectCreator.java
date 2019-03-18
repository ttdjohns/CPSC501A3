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
				+ "   C - An object containing a collection class");
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
	
	public void createOP() {
		System.out.println("Give the class name for new object (id# " + nextID + "):");
		String cls = s.nextLine();
		UserObject obj = new UserObject(cls, nextID, "OP");
		int objID = nextID;
		boolean fin = false;
		while (!fin) {
			System.out.println("Create primitive fields for " + cls + " (id# " + objID + ") in the form <name> <value> or '$$' to finish");
			String line = s.nextLine();
			String[] sLine = line.split(" ");
			if (line.equals("$$")) {
				fin = true;
			}
			else if (sLine.length != 2) {
				System.out.println("Incorrect formatting; Form <name> <value> or '$$' to finish"); 
			}
			else if (!(isNum(sLine[1]) || isChar(sLine[1]))){
				System.out.println("value was not a primitive");
			}
			else {
				obj.fields.add(new UserField(obj.cls, sLine[0], sLine[1]));
			}
		}
		prepareNextID();
	}
	private boolean isNum(String str) {
		try {
			Double d = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private boolean isChar(String str) {
		if (str.length() == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public void createOR() {
		// TODO Auto-generated method stub
		
	}
	
	public void createAP() {
		// TODO Auto-generated method stub
		
	}
	
	public void createAO() {
		// TODO Auto-generated method stub
		
	}

	public void createCollection() {
		// TODO Auto-generated method stub
		
	}
	
	public void prepareNextID() {
		nextID++;
		boolean tryAgain = false;
		while (!tryAgain) {
			tryAgain = false;
			for (UserObject obj : objList) {
				if (obj.id == nextID) {
					nextID++;
					tryAgain = true;
					break;
				}
			}
		}
	}
}
