import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;

public class Serializer {
	ArrayList<Integer> done = new ArrayList<Integer>();
	Element root = new Element("serialized");
	
	public org.jdom2.Document serialize(Object norm_obj) {
		if (!(norm_obj instanceof List<?>)) {
			System.out.println("cannot serialize an object that is not a UserObject");
			System.exit(-1);
		}
		@SuppressWarnings("unchecked")
		ArrayList<UserObject> objList = (ArrayList<UserObject>) norm_obj;
		for (UserObject obj : objList) {
			serial(obj);
		}
		
		return new org.jdom2.Document(root);
	}
	
	public void serial(UserObject obj) {
		Element ele = new Element("object");
		ele.setAttribute("class", obj.cls);
		ele.setAttribute("id", String.valueOf(obj.id));
		done.add(obj.id);
		
		if (obj.fields.size() > 0 && obj.fields.get(0).isPrim) {
			for (UserField i : obj.fields) {
				Element e = new Element("field");
				e.setAttribute("name", i.name);
				e.setAttribute("declaringclass", i.declaringclass);
				e.addContent((new Element("value")).setText(i.value));
				ele.addContent(e);
			}
		}
		else if (obj.fields.size() > 0 && obj.fields.get(0).isObject) {
			for (UserField i : obj.fields) {
				Element e = new Element("field");
				e.setAttribute("name", i.name);
				e.setAttribute("declaringclass", i.declaringclass);
				e.addContent((new Element("reference")).setText(String.valueOf(i.reference)));
				ele.addContent(e);
			}
		} 
		else if (obj.primArray.size() > 0 && obj.arraySize > 0) {
			ele.setAttribute("length", String.valueOf(obj.arraySize));
			for (int i = 0; i < obj.arraySize; i++) {
				if (i < obj.primArray.size()) {
					ele.addContent((new Element("value")).setText(obj.primArray.get(i)));
				} 
				else {
					ele.addContent((new Element("value")).setText("0"));
				}
			}
		}
		else if (obj.objArray.size() > 0 && obj.arraySize > 0) {
			ele.setAttribute("length", String.valueOf(obj.arraySize));
			for (int i = 0; i < obj.arraySize; i++) {
				if (i < obj.objArray.size()) {
					ele.addContent((new Element("reference")).setText(String.valueOf(obj.objArray.get(i))));
				} 
				else {
					ele.addContent((new Element("reference")).setText("NULL"));
				}
			}
		}
		else {
			for (int i = 0; i < obj.objArray.size(); i++) {
				ele.addContent((new Element("reference")).setText(String.valueOf(obj.objArray.get(i))));
			}
		}
		
		root.addContent(ele);
	}
}
