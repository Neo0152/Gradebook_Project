/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */

package project.Exceptions;

public class GradebookFullException extends Exception{
	
	private static final long serialVersionUID = 5624655493262295083L;

	public GradebookFullException() {}
	
	public GradebookFullException(String message) {
		super (message);
	}

}
