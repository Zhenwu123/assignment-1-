package entity;
/**
 * This class is a sub-class of base-class Profile 
 * which is used to store Adult profile information.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

public class Adult extends Profile{
	
	public Adult(String name, int age, String status, String image) {
		super(name, age, status, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
