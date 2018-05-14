package exception;

/**
 * This class is an exception class for the situation when
 *  trying to connect a child in a colleague relation.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

@SuppressWarnings("serial")
public class NotToBeColleaguesException extends Exception{

	public NotToBeColleaguesException(String msg) {
		super(msg);
	}
	
}