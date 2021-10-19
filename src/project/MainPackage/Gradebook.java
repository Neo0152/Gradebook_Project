package project.MainPackage;

import project.ObjectClassesAndInterfaces.*;
import project.Exceptions.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gradebook {

	public static void main(String[] args) {
		System.out.println("Welcome to the Gradebook Program\n"
				+ "This program was designed by Neil Kuehn\n\n");
		
		Scanner inputScanner = new Scanner(System.in);
		
		
		int gradeBookSize = 0;
		Boolean runGradeBook = true;
		int menuChoice = 0;
		int gradeBookCounter = 0;
	
		System.out.println("*Note: there is a maximum of 20 grades*\n"
				         + "How many grades would you like to enter?: ");
		
		do{
			while(!(inputScanner.hasNextInt())) {
				System.out.println("INVALID INPUT: Please Enter A Non-zero "
						+ "Positive Integer Thats Less Than 20: ");
				inputScanner.nextLine();
			}
			gradeBookSize = inputScanner.nextInt();
			if((gradeBookSize < 1) || (gradeBookSize > 20)) {
				System.out.println("INVALID INPUT: Please Enter A Non-zero "
						+ "Positive Integer Thats Less Than 20:  ");
			}
		} while ((gradeBookSize < 1) || (gradeBookSize > 20));
		
		AssinmentInterface[] gradeBook = new AssinmentInterface[gradeBookSize];
		
		
		do {
			menuChoice = gradeBookMenu();
		
			
			switch(menuChoice) {
			case 1:
				//add an full error 
				String gradeType = null;
				System.out.println("What kind of Assingnment do you want to add? (discussion,program,quiz): ");
				do {
					while((!inputScanner.hasNext("discussion")) && (!inputScanner.hasNext("program")) && (!inputScanner.hasNext("quiz") )) {
						System.out.println("INVALID INPUT: Please Enter A Valid Choice (discussion,program,quiz): ");
						inputScanner.next();
					}
					gradeType = inputScanner.nextLine();
				}while(!(gradeType.equals("discussion")) && !gradeType.equals("program") && !gradeType.equals("quiz"));
				
				switch(gradeType) {
				case "discussion":
					gradeBook[gradeBookCounter] = new Discussion();
					System.out.println("What Is The Assignment Name?: ");
					gradeBook[gradeBookCounter].setAssignmentName(inputScanner.nextLine());
					System.out.println("What Was The Assigned Reading?");
					((Discussion) gradeBook[gradeBookCounter]).setReading(inputScanner.nextLine());
					System.out.println("When Was The Assingment Due? (in yyyy-mm-dd format): ");
					String DueDate1 = null;
					int year1 = 0;
					int month1 = 0;
					int day1 = 0;
					do {
						while(!(inputScanner.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
							System.out.println("INVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
							inputScanner.nextLine();
						}
						DueDate1 = inputScanner.nextLine();
						Scanner dateScanner = new Scanner(DueDate1);
						dateScanner.useDelimiter("-");
						year1 = Integer.parseInt(dateScanner.next());
						month1 = Integer.parseInt(dateScanner.next());
						day1 = Integer.parseInt(dateScanner.next());
						
						switch(month1) {
						case 2:
							if((year1 % 4) == 0 ) {
								if((year1 % 100) == 0) {
									if((year1 % 400) == 0) {										
										if(day1 > 29) {
											day1 = 0;
										} 
									}
									if(day1 > 28 && ((year1 % 400) != 0)) day1 = 0;
								} 
							} else if(day1 > 28) day1 = 0;
							break;
						case 4:
						case 6:
						case 9:
						case 11: 
							if(day1 > 30) day1 = 0;
							break;
						}
						
						
						if(!(year1 > 0) || !(month1 > 0) || !(month1 < 13) || !(day1 > 0) || !(day1 < 32)) {
							System.out.println("INVALID INPUT: Date was in correct format but was an invalid day."
									+ " Please Enter A Valid Date in the format yyyy-mm-dd: ");
						}
						dateScanner.close();
					}while(!(year1 > 0) || !(month1 > 0) || !(month1 < 13) || !(day1 > 0) || !(day1 < 32));
					
					LocalDate AssinDate = LocalDate.of(year1,month1,day1);
					gradeBook[gradeBookCounter].setDueDate(AssinDate);
					
					System.out.println("What Was The Grade On The Assinment?"
							+ " (include .0 for whole numbers and stay in the range of 0.0 and 100.0)");
					double perGrade1;
					
					do {
						while(!inputScanner.hasNextDouble()) {
							System.out.println("INVALID INPUT: Please Enter A decimal grade including .0 for whole number grades");
							perGrade1 = Double.parseDouble(inputScanner.next());
						}
						perGrade1 = Double.parseDouble(inputScanner.next());
						if( (perGrade1 < 0.0) || (perGrade1 > 100.0) ) {
							System.out.println("INVALID INPUT: Correct Format But The Decimal Wasnt within the 0.0-100.0 range");
						}
					
					}while(perGrade1 < 0.0 || perGrade1 > 100.0);
					
					gradeBook[gradeBookCounter].setPercentGrade(perGrade1);
					gradeBook[gradeBookCounter].setLetterGrade(gradeLetterCalculate(perGrade1));
					gradeBookCounter++;
					break;
				case "program":

					gradeBook[gradeBookCounter] = new Program();
					System.out.println("What Is The Assignment Name?: ");
					gradeBook[gradeBookCounter].setAssignmentName(inputScanner.nextLine());
					System.out.println("What Is The Concept To Be Tested?: ");
					((Program) gradeBook[gradeBookCounter]).setConcept(inputScanner.nextLine());
					System.out.println("When Was The Assingment Due? (in yyyy-mm-dd format): ");
					String DueDate2 = null;
					int year2 = 0;
					int month2 = 0;
					int day2 = 0;
					do {
						while(!(inputScanner.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
							System.out.println("INVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
							inputScanner.nextLine();
						}
						DueDate2 = inputScanner.nextLine();
						Scanner dateScanner2 = new Scanner(DueDate2);
						dateScanner2.useDelimiter("-");
						year2 = Integer.parseInt(dateScanner2.next());
						month2 = Integer.parseInt(dateScanner2.next());
						day2 = Integer.parseInt(dateScanner2.next());
						
						switch(month2) {
						case 2:
							if((year2 % 4) == 0 ) {
								if((year2 % 100) == 0) {
									if((year2 % 400) == 0) {										
										if(day2 > 29) {
											day2 = 0;
										} 
									}
									if(day2 > 28 && ((year2 % 400) != 0)) day2 = 0;
								} 
							} else if(day2 > 28) day2 = 0;
							break;
						case 4:
						case 6:
						case 9:
						case 11: 
							if(day2 > 30) day2 = 0;
							break;
						}
						
						
						if(!(year2 > 0) || !(month2 > 0) || !(month2 < 13) || !(day2 > 0) || !(day2 < 32)) {
							System.out.println("INVALID INPUT: Date was in correct format but was an invalid day."
									+ " Please Enter A Valid Date in the format yyyy-mm-dd: ");
						}
						dateScanner2.close();
					}while(!(year2 > 0) || !(month2 > 0) || !(month2 < 13) || !(day2 > 0) || !(day2 < 32));
					
					LocalDate AssinDate2 = LocalDate.of(year2,month2,day2);
					gradeBook[gradeBookCounter].setDueDate(AssinDate2);
					
					System.out.println("What Was The Grade On The Assinment?"
							+ " (include .0 for whole numbers and stay in the range of 0.0 and 100.0)");
					double perGrade2;
					
					do {
						while(!inputScanner.hasNextDouble()) {
							System.out.println("INVALID INPUT: Please Enter A decimal grade including .0 for whole number grades");
							perGrade2 = Double.parseDouble(inputScanner.next());
						}
						perGrade2 = Double.parseDouble(inputScanner.next());
						if( (perGrade2 < 0.0) || (perGrade2 > 100.0) ) {
							System.out.println("INVALID INPUT: Correct Format But The Decimal Wasnt within the 0.0-100.0 range");
						}
					}while(perGrade2 < 0.0 || perGrade2 > 100.0);
					
					gradeBook[gradeBookCounter].setPercentGrade(perGrade2);
					gradeBook[gradeBookCounter].setLetterGrade(gradeLetterCalculate(perGrade2));
					gradeBookCounter++;
					break;
				case "quiz":
					gradeBook[gradeBookCounter] = new Quiz();
					System.out.println("What Is The Assignment Name?: ");
					gradeBook[gradeBookCounter].setAssignmentName(inputScanner.nextLine());
					System.out.println("How many Questions where on the quiz?: ");
					int amountOfQuestions = 0;
					
					do {
						while((!(inputScanner.hasNextInt())) && (amountOfQuestions < 1)) {
							System.out.println("INVALID INPUT: Please Enter A Non-zero Positive Integer: ");
							inputScanner.next();
						}
						amountOfQuestions = inputScanner.nextInt();
					}while(amountOfQuestions < 1);
					((Quiz) gradeBook[gradeBookCounter]).setNumQues(amountOfQuestions);
					
					inputScanner.nextLine();
					System.out.println("When Was The Assingment Due? (in yyyy-mm-dd format): ");
					String DueDate3 = null;
					int year3 = 0;
					int month3 = 0;
					int day3 = 0;
					do {
						while(!(inputScanner.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
							System.out.println("INVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
							inputScanner.nextLine();
						}
						DueDate3 = inputScanner.nextLine();
						Scanner dateScanner3 = new Scanner(DueDate3);
						dateScanner3.useDelimiter("-");
						year3 = Integer.parseInt(dateScanner3.next());
						month3 = Integer.parseInt(dateScanner3.next());
						day3 = Integer.parseInt(dateScanner3.next());
						
						switch(month3) {
						case 2:
							if((year3 % 4) == 0 ) {
								if((year3 % 100) == 0) {
									if((year3 % 400) == 0) {										
										if(day3 > 29) {
											day3 = 0;
										} 
									}
									if(day3 > 28 && ((year3 % 400) != 0)) day3 = 0;
								} 
							} else if(day3 > 28) day3 = 0;
							break;
						case 4:
						case 6:
						case 9:
						case 11: 
							if(day3 > 30) day3 = 0;
							break;
						}
						
						
						if(!(year3 > 0) || !(month3 > 0) || !(month3 < 13) || !(day3 > 0) || !(day3 < 32)) {
							System.out.println("INVALID INPUT: Date was in correct format but was an invalid day."
									+ " Please Enter A Valid Date in the format yyyy-mm-dd: ");
						}
						dateScanner3.close();
					}while(!(year3 > 0) || !(month3 > 0) || !(month3 < 13) || !(day3 > 0) || !(day3 < 32));
					
					LocalDate AssinDate3 = LocalDate.of(year3,month3,day3);
					gradeBook[gradeBookCounter].setDueDate(AssinDate3);
					
					System.out.println("What Was The Grade On The Assinment?"
							+ " (include .0 for whole numbers and stay in the range of 0.0 and 100.0)");
					double perGrade3;
					
					do {
						while(!inputScanner.hasNextDouble()) {
							System.out.println("INVALID INPUT: Please Enter A decimal grade including .0 for whole number grades");
							inputScanner.next();
						}
						perGrade3 = Double.parseDouble(inputScanner.next());
						if( (perGrade3 < 0.0) || (perGrade3 > 100.0) ) {
							System.out.println("INVALID INPUT: Correct Format But The Decimal Wasnt within the 0.0-100.0 range");
						}
					}while(perGrade3 < 0.0 || perGrade3 > 100.0);
					
					gradeBook[gradeBookCounter].setPercentGrade(perGrade3);
					gradeBook[gradeBookCounter].setLetterGrade(gradeLetterCalculate(perGrade3));
					gradeBookCounter++;
					
					break;
				}
				
				break;
			case 2:
				//add an empty error and invalid choice error 
				String removeAssinment = null;
				int deleteAssinment = -1;
				System.out.println(gradeBookCounter);
				System.out.println("What Assinment Would You Like To Delete?: ");
				removeAssinment = inputScanner.next();
				for(int i = 0; i<gradeBookCounter;i++) {
					if(removeAssinment.compareTo(gradeBook[i].getAssignmentName()) == 0) {
						deleteAssinment = i;
					}
				}
				
				AssinmentInterface[] gradeBookTemp = new AssinmentInterface[gradeBookSize];
				
				
				for(int i = 0, c = 0; c<(gradeBookCounter-1);i++, c++) {
					if(c == deleteAssinment) {
						c++;
					}
					gradeBookTemp[i] = gradeBook[c];
				}
				
				gradeBook = gradeBookTemp;
				gradeBookCounter--;
				
				break;
			case 3:
				//add an empty error 
				String InfoString = null;
				for(int i = 0;i<gradeBookCounter;i++) {
					if(gradeBook[i] instanceof Discussion) {
						InfoString = gradeBook[i].toString();
					}
					if(gradeBook[i] instanceof Program) {
						InfoString = gradeBook[i].toString();
					}
					if(gradeBook[i] instanceof Quiz) {
						InfoString = gradeBook[i].toString();
					}
					
					Scanner printScanner = new Scanner(InfoString);
					printScanner.useDelimiter(",");
					
					System.out.println("\n--------------------------------------"
									  +"\n" + printScanner.next()
									  +"\n" + printScanner.next()
									  +"\n" + printScanner.next()
									  +"\n" + printScanner.next()
									  +"\n" + printScanner.next()
									  +"\n--------------------------------------");
				}
				break;
			case 4:
				//add an empty error 
				double gradeAverage = 0.0;
				for(int i =0;i<gradeBookCounter;i++) {
					gradeAverage += gradeBook[i].getPercentGrade();
				}
				
				gradeAverage = gradeAverage/(gradeBookCounter);
				
				System.out.println("\nGrade Average: " + gradeAverage);
				break;
			case 5:
				//add an empty errror 
				double highestGrade = 0.0;
				double lowestGrade = 0.0;
				double tempGrade =0.0;
				
				if(gradeBookCounter == 1) {
					highestGrade = lowestGrade = gradeBook[0].getPercentGrade();
				} else {
					for(int i =0;i<gradeBookCounter;i++) {
						tempGrade = gradeBook[i].getPercentGrade();
						if(tempGrade > highestGrade) {
							highestGrade = tempGrade;
						}
						if(tempGrade < lowestGrade) {
							lowestGrade = tempGrade;
						}
					}
				}
				
				System.out.println("\nHighest Grade: " + (String.format("%,.2f",highestGrade))
								 + "\nLowest Grade: " + (String.format("%,.2f",lowestGrade)));
				
				break;
			case 6:
				//add an empty error 
				
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				runGradeBook = false;
				System.out.println("\nGoodbye!");
				break;
			}
		}while(runGradeBook);
		
		//inputScanner.close();
	}

	private static Reader InputStreamReader(InputStream in) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int gradeBookMenu() {
		Scanner menuInput = new Scanner(System.in);
		int menuChoice = -1;
		
		System.out.println("\nPlease Pick A Program Function\n"
				         + "---------------------------------------------------------------\n"
				         + "1-Add A Grade In The Gradebook\n"
				         + "2-Remove A Grade In The Gradebook\n"
				         + "3-Print All Of The Grades In The Gradebook\n"
				         + "4-Print Out The Average of All The Grades In The Gradebook\n"
				         + "5-Print The Highest And Lowest Score(s) In The Gradebook\n"
				         + "6-Print The Average Amount Of Questions Amongst All The quizzes\n"
				         + "7-Print All Of The Associated Readings From The Discussions\n"
				         + "8-Print All Of The Concepts Covered From The Programs\n"
				         + "9-Quit The Program\n"
				         + "---------------------------------------------------------------\n\n"
				         + "Menu Choice: ");
		do {
			while(!(menuInput.hasNextInt())) {
				System.out.println("INVALID INPUT: Please Enter A Valid Integer For The Menu Choice: ");
				menuInput.next();
			}
			menuChoice = menuInput.nextInt();
			if((menuChoice < 1) || (menuChoice > 9)) {
				System.out.println("INVALID MENU CHOICE: Please Enter A Valid Menu Option: ");
			}
		}while((menuChoice < 1) || (menuChoice > 9));
		
		
		return(menuChoice);
	}
	
	public static char gradeLetterCalculate(double perGrade) {
		char letGrade = 'F';
		
		if(perGrade <= 100 && perGrade > 89) {
			letGrade = 'A';
		} else if(perGrade < 90 && perGrade > 79) {
			letGrade = 'B';
		} else if(perGrade < 80 && perGrade > 69) {
			letGrade = 'C';
		} else if(perGrade < 70 && perGrade > 59) {
			letGrade = 'D';
		} else if(perGrade < 60) {
			letGrade = 'F';
		}
		return(letGrade);
	}
	
	public static void addAGrade() {
		
	}	
}
	

