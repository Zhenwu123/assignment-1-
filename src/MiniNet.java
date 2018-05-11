import java.util.ArrayList;

import entity.Connection;
import entity.MiniNetManager;
import entity.Profile;
import utlility.FileUtility;
import utlility.IOUtility;
/**
 * This class is a main startup class which read sample data and build up a MiniNet, 
 * and then receive user input to manipulate the MiniNet. 
 * 
 * @author  Zhen Wu
 * @version 1.0
 */

public class MiniNet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ArrayList<Profile> profiles = IOUtility.readProfiles();
		ArrayList<Profile> profiles = FileUtility.buildProfileListFromFile("people.txt");
		ArrayList<Connection> connections = FileUtility.buildConnectionListFromFile("relations.txt", profiles);
		MiniNetManager manager = new MiniNetManager(profiles, connections);
		Driver driver = new Driver(manager);
		driver.run();
	}
}
