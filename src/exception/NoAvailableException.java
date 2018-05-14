package exception;

/**
 * This class is an exception class for the situation when 
 * trying to make two adults a couple and at least one of them 
 * is already connected with another adult as a couple.  
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

@SuppressWarnings("serial")
public class NoAvailableException extends Exception{

	public NoAvailableException(String msg) {
		super(msg);
	}
	
}