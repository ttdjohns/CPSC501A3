
public class UserField {
	String declaringclass = "";
	String name = "";
	String value = "";
	int reference = 0;
	
	public UserField(String declaringclass, String name, String value) {
		this.declaringclass = declaringclass;
		this.name = name;
		this.value = value;
	}
	
	public UserField(String declaringclass, String name, int reference) {
		this.declaringclass = declaringclass;
		this.name = name;
		this.reference = reference;
	}
}
