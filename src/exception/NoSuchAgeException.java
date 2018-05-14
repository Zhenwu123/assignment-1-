package exception;

/**
 * This class is an exception class for the situation when 
 * trying to enter a person whose age is negative or over 150.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

@SuppressWarnings("serial")
public class NoSuchAgeException extends Exception{

	public NoSuchAgeException(String msg) {
		super(msg);
	}
	
}