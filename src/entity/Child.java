package entity;

import interf8ce.AddRelationship;

import java.util.ArrayList;
/**
 * This class is a sub-class of base-class Profile which is 
 * used to store Child profile information, and also it 
 * implements the AddRelationship interface to add Profile 
 * to its parents Profile ArrayList. 
 * 
 * @author  Zhen Wu
 * @version 1.0
 */

public class Child extends Profile implements AddRelationship{
	
    /** ArrayList<Profile> parents is used to store the 
     * parents' Profile of a child.
     */
	private ArrayList<Profile> parents;

	public Child(String name, int age, String status) {
		super(name, age, status);		
		// TODO Auto-generated constructor stub

		parents = new ArrayList<Profile>(2);
	}

	public ArrayList<Profile> getParents() {
		return parents;
	}

	public void setParents(ArrayList<Profile> parents) {
		this.parents = parents;
	}

	/** toString() build an String of all the information
	 *  of a Child profile.
     * 
     * @return  String  An object which holds the all the 
     * information of a Child profile.
     */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result;
		if(getParents().size() != 0){
			result = "Parents: " + getParents().get(0).getName() + " and " + getParents().get(1).getName() + "\n";
		}else{
			result = "Parents:" + "\n";
		}
		return super.toString() + result;
	}

	/** addParents() implements from AddRelationship interface
	 *  to add Profile into parents ArrayList.
     * 
     */
	@Override
	public void addParents(Profile profile) {
		// TODO Auto-generated method stub
		if(!parents.contains(profile)){
			parents.add(profile);
		}
	}
	
	public void printParents() {
		System.out.print(parents.get(0).getName() + " and " + parents.get(1).getName());
	}

}
