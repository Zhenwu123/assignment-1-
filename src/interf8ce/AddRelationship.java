package interf8ce;

import entity.Profile;
/**
 * This interface is for Profile's subclass Child to add parents to its member-variable.
 * 
 * @author Zhen Wu
 * @version 1.0
 */

public interface AddRelationship {

	public abstract void addParents(Profile profile);
	
}
