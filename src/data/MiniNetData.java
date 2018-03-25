package data;

import java.util.ArrayList;

import entity.Adult;
import entity.Child;
import entity.Connection;
import entity.Profile;
/**
 * This class is a class which creates sample data for MiniNet including 
 * list of profiles and list of connections. 
 * 
 * @author  
 * @version 1.0
 */

public class MiniNetData {
	
	private static Profile lucas, ivy, emily, alice, bob, cathy, don, jack, mia;
	
	public static ArrayList<Profile> createProfile(){
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		lucas = new Child("Lucas", 12, "High School Student");
		profiles.add(lucas);
		ivy = new Child("Ivy", 14, "High School Student");
		profiles.add(ivy);
		emily = new Adult("Emily", 22, "Working in KFC");
		profiles.add(emily);
		alice = new Adult("Alice", 14, "Student at RMIT");
		profiles.add(alice);
		bob = new Adult("Bob", 14, "Student at RMIT");
		profiles.add(bob);
		cathy = new Adult("Cathy", 13, "Student at RMIT");
		profiles.add(cathy);
		don = new Adult("Don", 13, "Student at RMIT");
		profiles.add(don);
		jack = new Adult("Jack", 45, "Working in ChinaTown");
		profiles.add(jack);
		mia = new Adult("Mia", 42, "Working as Accountant");
		profiles.add(mia);
		return profiles;
	}
	
	public static ArrayList<Connection> createConnection(){
		ArrayList<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(lucas, ivy, "friends"));
		connections.add(new Connection(alice, bob, "friends"));
		connections.add(new Connection(alice, don, "friends"));
		connections.add(new Connection(cathy, don, "friends"));
		connections.add(new Connection(ivy, jack, "parents"));
		connections.add(new Connection(ivy, mia, "parents"));
		return connections;
	}
}
