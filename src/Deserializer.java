import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;

public class Deserializer {
	Element root;
	List<UserObject> objList = new ArrayList<UserObject>();
	
	
	public Object deserialize(Document doc) {
		root = doc.getRootElement();
		List<Element> children = root.getChildren();
		for (Element c : children) {
			objList.add(deserialObj(c));
		}
		
		return new Object();
	}
	
	public UserObject deserialObj(Element ele) {
		
		String cls = ele.getAttributeValue("class");
		int id = Integer.parseInt(ele.getAttributeValue("id"));
		String arrayLenS = ele.getAttributeValue("length");
		int arrayLen = 0;
		if (arrayLenS != null) {
			arrayLen = Integer.parseInt(arrayLenS);
		}
		UserObject obj = new UserObject();
		obj.cls = cls;
		obj.id = id;
		obj.arraySize = arrayLen;
		List<Element> children = ele.getChildren();
		for (Element c : children) {
			if (c.getName().equals("field")) {
				String text = c.getChildText("value");
				if (text != null) {
					UserField f = new UserField(c.getAttributeValue("declaringclass"), c.getAttributeValue("name"), text);
					obj.fields.add(f);
				} else {
					text = c.getChildText("reference");
					if (!(text.contains("NULL"))) {
						UserField f = new UserField(c.getAttributeValue("declaringclass"), c.getAttributeValue("name"), Integer.parseInt(text));
						obj.fields.add(f);
					}
				}
				
			}
			else if ((obj.primArray.size() < obj.arraySize) && (c.getName().equals("value"))) {
				obj.primArray.add(c.getText());
			}
			else if ((obj.objArray.size() < obj.arraySize) && !(c.getText().contains("NULL"))){
				obj.objArray.add(Integer.parseInt(c.getText()));
			}
		}
		
		
		
		
		return obj;
	}
}
