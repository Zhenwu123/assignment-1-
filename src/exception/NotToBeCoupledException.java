package exception;

/**
 * This class is an exception class for the situation when
 *  trying to make a couple when at least one member is not 
 *  an adult.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

@SuppressWarnings("serial")
public class NotToBeCoupledException extends Exception{

	public NotToBeCoupledException(String msg) {
		super(msg);
	}
	
}