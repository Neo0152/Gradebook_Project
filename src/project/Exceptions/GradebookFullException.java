/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */

package project.Exceptions;

public class GradebookFullException extends Exception{
	public GradebookFullException() {}
	
	public GradebookFullException(String message) {
		super (message);
	}

}
