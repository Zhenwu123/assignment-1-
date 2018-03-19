import java.util.ArrayList;

import utlility.IOUtility;
import entity.Connection;
import entity.MiniNetManager;
import entity.Profile;


public class MiniNet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Profile> profiles = IOUtility.readProfiles();
		ArrayList<Connection> connections = IOUtility.readConnections();
		MiniNetManager manager = new MiniNetManager(profiles, connections);
		Driver driver = new Driver(manager);
		driver.run();
	}
}
