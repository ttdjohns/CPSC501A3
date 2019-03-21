import java.util.List;

public class Visualizer {

	public void display(List<UserObject> objList) {
		String str = "";
		for (UserObject obj : objList) {
			str += "Class: " + obj.cls + "\n";
			str += "id#: " + obj.id + "\n";
			if (obj.cls.contains("[")) {
				str += "array length: " + obj.arraySize + "\n";
				for (int i = 0; i < obj.arraySize; i++) {
					if (i < obj.objArray.size()) {
						str += ind(1) + "ObjectID: " + obj.objArray.get(i) + "\n";
					}
					else if (i < obj.primArray.size()) {
						str += ind(1) + "value: " + obj.primArray.get(i) + "\n";
					}
					else {
						str += ind(1) + "Null" + "\n";
					}
				}
			}
			else {
				for (int i = 0; i < obj.objArray.size(); i++) {
					str += ind(1) + "ObjectID: " + obj.objArray.get(i) + "\n";
				}
				for (UserField f : obj.fields) {
					str += ind(1) + "Field " + f.name;
					if (f.isPrim) {
						str += " is " + f.value + "\n";
					}
					else {
						str += " is " + f.reference + "\n";
					}
				}
			}
		}
		System.out.println(str);
	}
	
	public String ind(int num) {
		String ret = "";
		for (int i = 0; i <= num; i++) {
			ret += "   ";
		}
		return ret;
	}
}
