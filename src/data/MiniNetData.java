package data;

import java.util.ArrayList;

import entity.Adult;
import entity.Child;
import entity.Connection;
import entity.Profile;

public class MiniNetData {
	
	private static Child lucas, ivy;
	private static Adult emily, alice, bob, cathy, don, jack, mia;
	
	public static ArrayList<Profile> createProfile(){
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		lucas = new Child("Lucas", 12, "High School Student");
		profiles.add(lucas);
		ivy = new Child("Ivy", 14, "High School Student");
		profiles.add(ivy);
		emily = new Adult("Emily", 22, "Working in KFC");
		profiles.add(emily);
		alice = new Adult("Alice", 17, "Student at RMIT");
		profiles.add(alice);
		bob = new Adult("Bob", 17, "Student at RMIT");
		profiles.add(bob);
		cathy = new Adult("Cathy", 18, "Student at RMIT");
		profiles.add(cathy);
		don = new Adult("Don", 18, "Student at RMIT");
		profiles.add(don);
		jack = new Adult("Jack", 50, "Working in ChinaTown");
		profiles.add(jack);
		mia = new Adult("Mia", 48, "Working as Accountant");
		profiles.add(mia);
		return profiles;
	}
	
	public static ArrayList<Connection> createConnection(){
		ArrayList<Connection> connections = new ArrayList<Connection>();
		connections.add(new Connection(lucas, ivy, "friend"));
		connections.add(new Connection(emily, jack, "parents"));
		connections.add(new Connection(emily, mia, "parents"));
		connections.add(new Connection(alice, bob, "friend"));
		connections.add(new Connection(alice, don, "friend"));
		connections.add(new Connection(cathy, don, "friend"));
		return connections;
	}
}
