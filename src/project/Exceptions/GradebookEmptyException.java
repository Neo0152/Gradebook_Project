/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */

package project.Exceptions;

public class GradebookEmptyException extends Exception {

	private static final long serialVersionUID = 3290565931551523747L;

	public GradebookEmptyException() {}
	
	public GradebookEmptyException(String message) {
		super(message);
	}
	
}
