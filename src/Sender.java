import java.util.List;

public class Sender {


	public static void main(String[] args) {
		ObjectCreator oc = new ObjectCreator();
		oc.objCreator();
		List<UserObject> objList = oc.getObjList();
		Serializer s = new Serializer();
		s.serializer();
		
		//send over socket
		
	}
}
