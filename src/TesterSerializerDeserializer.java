import org.junit.Assert.*;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.junit.After;
import org.junit.Test;

public class TesterSerializerDeserializer {

	Serializer s;
	Deserializer ds;
	Visualizer v;
	UserObject oP;
	UserObject oR;
	UserObject aP;
	UserObject aO;
	UserObject col;
	
	@Before
	public void before() {
		s = new Serializer();
		ds = new Deserializer();
		v = new Visualizer();
		oP = new UserObject("oP", 0);
		for (int i = 1; i < 2; i++) {
			oP.fields.add(new UserField(oP.cls, "f" + i, String.valueOf(i)));
		}
		oR = new UserObject("oR", 1);
		for (int i = 1; i < 2; i++) {
			oR.fields.add(new UserField(oR.cls, "f" + i, oR.id));
		}
		aP = new UserObject("[F", 2);
		aP.arraySize = 3;
		for (int i = 0; i < 2; i++) {
			aP.primArray.add(String.valueOf(i));
		}
		aO = new UserObject("[Object", 3);
		aO.arraySize = 3;
		for (int i = 0; i < 2; i++) {
			aO.objArray.add(3);
		}
		col = new UserObject(ArrayList.class.getName(), 4);
		for (int i = 0; i < 2; i++) {
			col.objArray.add(4);
		}
		
	}
	
	@After
	public void after() {
		s = null;
		ds = null;
		v = null;
		oP = null;
		oR = null;
		aP = null;
		aO = null;
		col = null;
	}
	
	@Test
	public void testSerializeOP() {
		List<UserObject> list = new ArrayList<UserObject>();
		list.add(oP);
		Document doc = s.serialize(list);
		String str = new XMLOutputter().outputString(doc);
		System.out.println(str);
		assertTrue(str.contains(serOP));
	}
	

	@Test
	public void testSerializeOR() {
		List<UserObject> list = new ArrayList<UserObject>();
		list.add(oR);
		Document doc = s.serialize(list);
		String str = new XMLOutputter().outputString(doc);
		System.out.println(str);
		assertTrue(str.contains(serOR));
	}
	
	@Test
	public void testSerializeAP() {
		List<UserObject> list = new ArrayList<UserObject>();
		list.add(aP);
		Document doc = s.serialize(list);
		String str = new XMLOutputter().outputString(doc);
		System.out.println(str);
		assertTrue(str.contains(serAP));
	}
	
	@Test
	public void testSerializeAO() {
		List<UserObject> list = new ArrayList<UserObject>();
		list.add(aO);
		Document doc = s.serialize(list);
		String str = new XMLOutputter().outputString(doc);
		System.out.println(str);
		assertTrue(str.contains(serAO));
	}
	
	@Test
	public void testSerializeCol() {
		List<UserObject> list = new ArrayList<UserObject>();
		list.add(col);
		Document doc = s.serialize(list);
		String str = new XMLOutputter().outputString(doc);
		System.out.println(str);
		assertTrue(str.contains(serCol));
	}
	
	
	
	String serOP = "<serialized><object class=\"oP\" id=\"0\"><field name=\"f1\" declaringclass=\"oP\"><value>1</value></field></object></serialized>";
	String serOR = "<serialized><object class=\"oR\" id=\"1\"><field name=\"f1\" declaringclass=\"oR\"><reference>1</reference></field></object></serialized>";
	String serAP = "<serialized><object class=\"[F\" id=\"2\" length=\"3\"><value>0</value><value>1</value><value>0</value></object></serialized>";
	String serAO = "<serialized><object class=\"[Object\" id=\"3\" length=\"3\"><reference>3</reference><reference>3</reference><reference>NULL</reference></object></serialized>";
	String serCol = "<serialized><object class=\"java.util.ArrayList\" id=\"4\"><reference>4</reference><reference>4</reference></object></serialized>";
	
}
