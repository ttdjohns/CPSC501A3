import java.util.ArrayList;

public class UserObject {
	String cls;
	int id;
	//String objType;
	ArrayList<UserField> fields = new ArrayList<UserField>();
	ArrayList<Integer> objArray = new ArrayList<Integer>();
	ArrayList<String> primArray = new ArrayList<String>();
	int arraySize = 0;

	public UserObject(String cls, int id) {
		this.cls = cls;
		this.id = id;
		//this.objType = objType;
	}

	public UserObject() {
		// TODO Auto-generated constructor stub
	}
}
