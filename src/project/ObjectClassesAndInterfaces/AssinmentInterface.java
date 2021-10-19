/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */
package project.ObjectClassesAndInterfaces;

import java.time.LocalDate; 

public interface AssinmentInterface {
	public void setLetterGrade(char letGrade); 
	public void setPercentGrade(double perGrade);
	public void setAssignmentName(String AssignName);
	public void setDueDate(LocalDate DDate);	
	public char getLetterGrade();
	public double getPercentGrade();
	public String getAssignmentName();
	public LocalDate getDueDate();
	public String toString();
}
