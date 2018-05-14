package exception;

/**
 * This class is an exception class for the situation when
 *  trying to make an adult and a child friend or connect two
 *  children with an age gap larger than 3.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

@SuppressWarnings("serial")
public class NotToBeFriendsException extends Exception{

	public NotToBeFriendsException(String msg) {
		super(msg);
	}
	
}