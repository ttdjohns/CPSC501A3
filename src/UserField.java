
public class UserField {
	String declaringclass = "";
	String name = "";
	String value = "";
	int reference = 0;
	boolean isPrim = false;
	boolean isArray = false;
	boolean isObject = false;
	
	public UserField(String declaringclass, String name, String value) {
		this.declaringclass = declaringclass;
		this.name = name;
		this.value = value;
		isPrim = true;
	}
	
	public UserField(String declaringclass, String name, int reference) {
		this.declaringclass = declaringclass;
		this.name = name;
		this.reference = reference;
		isObject = true;
	}
}
