/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */

package project.Exceptions;

public class GradebookEmptyException extends Exception {
	public GradebookEmptyException() {}
	
	public GradebookEmptyException(String message) {
		super(message);
	}
	
}
