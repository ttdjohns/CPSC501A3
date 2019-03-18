import java.util.List;
import java.util.ArrayList;

public class UserObject {
	String cls;
	int id;
	String objType;
	ArrayList<Object> fields = new ArrayList<Object>();

	public UserObject(String cls, int id, String objType) {
		this.cls = cls;
		this.id = id;
		this.objType = objType;
		
	}
}
