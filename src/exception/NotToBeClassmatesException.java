package exception;

/**
 * This class is an exception class for the situation when 
 * trying to make a young child in a classmate relation.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

@SuppressWarnings("serial")
public class NotToBeClassmatesException extends Exception{

	public NotToBeClassmatesException(String msg) {
		super(msg);
	}
	
}