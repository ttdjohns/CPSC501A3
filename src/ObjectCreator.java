import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjectCreator {

	public List<UserObject> objList = new ArrayList<UserObject>();
	int nextID = 0;
	Scanner s = new Scanner(System.in);
	
	public UserObject objCreator() {
		boolean fin = false;
		UserObject ret = null;
		System.out.println("Select an option: \n"
				+ "   OP - Single object with primitives \n"
				+ "   OR - Single object with object references\n"
				+ "   AP - An object containing an Array of primitives \n"
				+ "   AO - An object containing an Array of objects \n"
				+ "   C - An object containing a collection class");
		while (!fin) {
			String line = s.nextLine();
			if (line.equals("OP")) {
				fin = true;
				ret = createOP();
			} 
			else if (line.equals("OR")) {
				fin = true;
				ret = createOR();
			}
			else if (line.equals("AP")) {
				fin = true;
				ret = createAP();
			}
			else if (line.equals("AO")) {
				fin = true;
				ret = createAO();
			}
			else if (line.equals("C")) {
				fin = true;
				ret = createCollection();
			}
			else {
				System.out.println("That was not a valid entry\n");
			}
		}
		return ret;
	}
	
	public UserObject createOP() {
		System.out.println("Give the class name for new object (id# " + nextID + "):");
		String cls = s.nextLine();
		UserObject obj = new UserObject(cls, nextID);
		objList.add(obj);
		prepareNextID();
		boolean fin = false;
		while (!fin) {
			System.out.println("Create primitive fields for " + cls + " (id# " + obj.id + ") in the form <name> <value> or '$$' to finish");
			String line = s.nextLine();
			String[] sLine = line.split(" ");
			if (line.equals("$$")) {
				fin = true;
			}
			else if (sLine.length != 2) {
				System.out.println("Incorrect formatting; Form <name> <value> or '$$' to finish"); 
			}
			else if (!(isChar(sLine[1]) || isNum(sLine[1]))){
				System.out.println("value was not a primitive");
			}
			else if (nameExists(obj, sLine[0])) {
				System.out.println("Field name already exists");
			}
			else {
				obj.fields.add(new UserField(obj.cls, sLine[0], sLine[1]));
			}
		}
		return obj;
	}
	

	public UserObject createOR() {
		System.out.println("Give the class name for new object (id# " + nextID + "):");
		String cls = s.nextLine();
		UserObject obj = new UserObject(cls, nextID);
		objList.add(obj);
		prepareNextID();
		boolean fin = false;
		while (!fin) {
			System.out.println("Create object reference fields for " + cls + " (id# " + obj.id + ")\n"
					+ "  Use the form <name> <refID#> for an existing object\n"
					+ "  Use the form <name> N   for a new object\n"
					+ "   or '$$' to finish");
			String line = s.nextLine();
			String[] sLine = line.split(" ");
			if (line.equals("$$")) {
				fin = true;
			}
			else if (sLine.length != 2) {
				System.out.println("Incorrect formatting; Form <name> <refID#>/N or '$$' to finish"); 
			}
			else if (nameExists(obj, sLine[0])) {
				System.out.println("Field name already exists");
			}
			else if (sLine[1].equals("N")) {
				UserObject returnedObj = objCreator();
				obj.fields.add(new UserField(obj.cls, sLine[0], returnedObj.id));
			}
			else if (!checkObjCreated(sLine[1])){
				System.out.println("refID# " + sLine[1] + " does not exist");
			}
			else {
				obj.fields.add(new UserField(obj.cls, sLine[0], Integer.parseInt(sLine[1])));
			}
		}
		return obj;
	}

	public UserObject createAP() {
		boolean fin = false;
		String cls = "";
		while (!fin) {
			System.out.println("Give the array components type for new object (id# " + nextID + "): (B,C,D,F,I,J,S,Z)");
			cls = s.nextLine();
			if (cls.equals("B") || cls.equals("C") || cls.equals("D") || cls.equals("F") 
					|| cls.equals("I") || cls.equals("J") || cls.equals("S") || cls.equals("Z")) {
				fin = true;
			}
			else {
				System.out.println("Invalid primitive component type");
			}
		}
		UserObject obj = new UserObject("[" + cls, nextID);
		objList.add(obj);
		prepareNextID();
		
		fin = false;
		while (!fin) {
			System.out.println("Set the array size for " + cls + " (id# " + obj.id + ")");
			String line = s.nextLine();
			if (!isInt(line)) {
				System.out.println("That was not an integer");
			}
			else {
				obj.arraySize = Integer.parseInt(line);
				fin = true;
			}
		}
		fin = false;
		
		while (!fin && obj.arraySize > obj.primArray.size()) {
			System.out.println("Create primitive value for [" + cls + " (id# " + obj.id + "). '$$' to finish");
			String line = s.nextLine();
			if (line.equals("$$")) {
				fin = true;
			}
			else if (cls.equals("C") && !isChar(line)) {
				System.out.println("Incorrect input; single characture or '$$' to finish"); 
			}
			else if (!cls.equals("C") && !isNum(line)) {
				System.out.println("Incorrect input; number or '$$' to finish"); 
			}
			else {
				obj.primArray.add(line);
			}
		}
		return obj;
	}
	
	public UserObject createAO() {
		String cls = "[Object";
		UserObject obj = new UserObject(cls, nextID);
		objList.add(obj);
		prepareNextID();
		boolean fin = false;
		while (!fin) {
			System.out.println("Set the array size for " + cls + " (id# " + obj.id + ")");
			String line = s.nextLine();
			if (!isInt(line)) {
				System.out.println("That was not an integer");
			}
			else {
				obj.arraySize = Integer.parseInt(line);
				fin = true;
			}
		}
		fin = false;
		while (!fin && obj.arraySize > obj.objArray.size()) {
			System.out.println("Create object reference fields for " + cls + " (id# " + obj.id + ")\n"
					+ "  Enter <refID#> for an existing object\n"
					+ "  Enter N   for a new object\n"
					+ "   or '$$' to finish");
			String line = s.nextLine();
			if (line.equals("$$")) {
				fin = true;
			}
			else if (line.equals("N")) {
				UserObject returnedObj = objCreator();
				obj.objArray.add(returnedObj.id);
			}
			else if (!isInt(line) || !checkObjCreated(line)){
				System.out.println("refID# " + line + " does not exist");
			}
			else {
				obj.objArray.add(Integer.parseInt(line));
			}
		}
		return obj;
	}

	public UserObject createCollection() {
		String cls = ArrayList.class.getName();
		UserObject obj = new UserObject(cls, nextID);
		objList.add(obj);
		prepareNextID();
		boolean fin = false;
		while (!fin) {
			System.out.println("Create object reference fields for " + cls + " (id# " + obj.id + ")\n"
					+ "  Enter <refID#> for an existing object\n"
					+ "  Enter N   for a new object\n"
					+ "   or '$$' to finish");
			String line = s.nextLine();
			if (line.equals("$$")) {
				fin = true;
			}
			else if (line.equals("N")) {
				UserObject returnedObj = objCreator();
				obj.objArray.add(returnedObj.id);
			}
			else if (!isInt(line) || !checkObjCreated(line)){
				System.out.println("refID# " + line + " does not exist");
			}
			else {
				obj.objArray.add(Integer.parseInt(line));
			}
		}
		return obj;
	}
	
	public UserObject getObjFromID(int id) {
		for (UserObject obj : objList) {
			if (obj.id == id) {
				return obj;
			}
		}
		return null;
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
	
	private boolean checkObjCreated(String string) {
		int id = 999999999;
		try {
			id = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return false;
		}
		for (UserObject obj : objList) {
			if (obj.id == id) {
				return true;
			}
		}
		return false;
	}
	
	private boolean nameExists(UserObject obj, String fName) {
		for (UserField fields : obj.fields) {
			if (fields.name.equals(fName)) {
				return true;
			}
		}
		return false;
	}

	private boolean isNum(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private boolean isInt(String str) {
		try {
			Integer.parseInt(str);
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
	
	public List<UserObject> getObjList() {
		return objList;
	}
}
