import java.util.ArrayList;

public class UserObject {
	String cls;
	int id;
	String objType;
	ArrayList<UserField> fields = new ArrayList<UserField>();
	ArrayList<UserObject> objArray = new ArrayList<UserObject>();
	ArrayList<String> primArray = new ArrayList<String>();
	int arraySize = 0;

	public UserObject(String cls, int id, String objType) {
		this.cls = cls;
		this.id = id;
		this.objType = objType;
	}
}
