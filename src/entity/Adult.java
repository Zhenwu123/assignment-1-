package entity;

import java.util.ArrayList;

public class Adult extends Profile {
	
	private ArrayList<Profile> child;

	public Adult(String name, int age, String status) {
		super(name, age, status);
		// TODO Auto-generated constructor stub
		
		child = new ArrayList<Profile>();
	}

	public ArrayList<Profile> getChild() {
		return child;
	}

	public void setChild(ArrayList<Profile> child) {
		this.child = child;
	}

	@Override
	public void print2Screen() {
		// TODO Auto-generated method stub

	}

}
