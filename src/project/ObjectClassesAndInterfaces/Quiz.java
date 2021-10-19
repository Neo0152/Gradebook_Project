/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */
package project.ObjectClassesAndInterfaces;

import java.time.LocalDate;

public class Quiz implements AssinmentInterface{

	private char LetterGrade;
	private double PercentGrade;
	private LocalDate DueDate;
	private String assignmentName;
	private int numberOfQuestions;
	
	@Override
	public void setLetterGrade(char letGrade) {
		this.LetterGrade = letGrade;
	}

	@Override
	public void setPercentGrade(double perGrade) {
		this.PercentGrade = perGrade;
		
	}

	@Override
	public void setAssignmentName(String Assignment) {
		this.assignmentName = Assignment;
	}

	@Override
	public void setDueDate(LocalDate DDate) {
		this.DueDate = DDate;
		
	}

	@Override
	public char getLetterGrade() {
		return(this.LetterGrade);
	}

	@Override
	public double getPercentGrade() {
		return(this.PercentGrade);
	}

	@Override
	public String getAssignmentName() {
		return(this.assignmentName);
	}

	@Override
	public LocalDate getDueDate() {
		return(this.DueDate);
	}
	
	@Override
	public String toString() {
		String TOstring = "Name: " + this.assignmentName +",Score: " + this.PercentGrade + ",Letter: "
				+ this.LetterGrade + ",Due: " + this.DueDate + ",Number of questions: " + this.numberOfQuestions;
		return(TOstring);
	}
	
	public void setNumQues(int Num) {
		this.numberOfQuestions = Num;
	}
	
	public int getNumQues() {
		return(this.numberOfQuestions);
	}

}
