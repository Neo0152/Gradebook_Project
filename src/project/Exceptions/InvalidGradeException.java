/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */

package project.Exceptions;

public class InvalidGradeException extends Exception{

	private static final long serialVersionUID = -325179538431921708L;

	public InvalidGradeException() {}
	
	public InvalidGradeException(String message) {
		super (message);
	}

}
