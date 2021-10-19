package project.ObjectClassesAndInterfaces;

import java.time.LocalDate;


public class Program implements AssinmentInterface {

	private char LetterGrade;
	private double PercentGrade;
	private LocalDate DueDate;
	private String assignmentName;
	private String conceptToBeTested;
	
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
				+ this.LetterGrade + ",Due: " + this.DueDate + ",ConceptToBeTested:  " + this.conceptToBeTested;
		return(TOstring);
	}

	public void setConcept(String newConcept) {
		this.conceptToBeTested = newConcept;
	}
	
	public String getConcept() {
		return(this.conceptToBeTested);
	}
}
