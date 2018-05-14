package exception;

/**
 * This class is an exception class for the situation when a child 
 * or young child has no parent or has only one parent, e.g.adding
 *  a child to one adult, or to two adults who are not a couple. 
 *  That also happens when trying to delete an adult who has at 
 *  least one dependent. (In this world a couple that have at least 
 *  one kid become inseparable and immortal.)
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

@SuppressWarnings("serial")
public class NoParentException extends Exception{

	public NoParentException(String msg) {
		super(msg);
	}
	
}