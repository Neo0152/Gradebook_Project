/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */
package project.ObjectClassesAndInterfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import project.Toolbox.Console;


public class Program implements AssinmentInterface {

	private char LetterGrade;
	private double PercentGrade;
	private LocalDate DueDate;
	private String assignmentName;
	private String conceptToBeTested;
	
	public Program(char LetGrade, double PerGrade, LocalDate DD, String assigName, String CTBT) {
		LetterGrade = LetGrade;
		PercentGrade = PerGrade;
		DueDate = DD;
		assignmentName = assigName;
		conceptToBeTested = CTBT;
	}
	
	public Program(String inputToString) {
		ArrayList<String> Parts = new ArrayList<>();
		Scanner printScanner = new Scanner(inputToString);
		printScanner.useDelimiter(",");
		
		while(printScanner.hasNext()) {
			Parts.add(printScanner.next());
		}
		assignmentName = Parts.get(0);
		PercentGrade = Double.valueOf(Parts.get(1));
		LetterGrade = (Parts.get(2).toCharArray())[0];
		DueDate = Console.StringToDate(Parts.get(3));
		conceptToBeTested = Parts.get(4);
		printScanner.close();
	}
	
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
		String TOstring = this.assignmentName +"," + this.PercentGrade + ","
				+ this.LetterGrade + "," + this.DueDate +"," + this.conceptToBeTested;
		return(TOstring);
	}

	public void setConcept(String newConcept) {
		this.conceptToBeTested = newConcept;
	}
	
	public String getConcept() {
		return(this.conceptToBeTested);
	}
}
