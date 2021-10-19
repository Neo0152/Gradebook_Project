/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */

package project.Exceptions;

public class InvalidGradeException extends Exception{
	public InvalidGradeException() {}
	
	public InvalidGradeException(String message) {
		super (message);
	}

}
