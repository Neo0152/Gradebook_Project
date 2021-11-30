package project.Toolbox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import project.MainPackage.GradebookHelperFunctions;

public class Console {
	
	private static Scanner input = new Scanner(System.in);

	public static int getInt(String prompt) {
		int number = 0;
	    do{	            
	    	System.out.print(prompt);	            
	    	try {
	    		number = Integer.parseInt(input.nextLine());
	    		break;
	    	} catch (NumberFormatException e) {
	    		System.out.println("\tError! Invalid input. Try again.");   
	    	}   
	    }while(true);
	    return number;   
	}
	
	public static double getdouble(String prompt) {
		double number = 0.0;
		do {
			System.out.print(prompt);
			try {
				number = Double.parseDouble(input.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("\tError! Invalid input. Try again.");
			}
		}while(true);
		return number;
	}
	
    public static String getRequiredString(String prompt) {
        String string = "";
        while (true) {
            System.out.print(prompt);
            string = input.nextLine();
            if (string.equals("")) {
                System.out.println("\tError! This entry is required. Try again.");
            } else {
                break;
            }
        }
        return string;
    }
	
    public static String getAssinmentType(String prompt) {
        String s = "";
        boolean isValid = false;
        while (isValid == false) {
            s = getRequiredString(prompt);
            if (!s.equalsIgnoreCase("quiz")
                    && !s.equalsIgnoreCase("discussion")
                    && !s.equalsIgnoreCase("program")) {
                System.out.println("\tError! Entry must be '"
                        + "quiz" + "', '"
                        + "discussion" + "', or '"
                        + "program" + "'. Try again.");
            } else {
                isValid = true;
            }
        }
        return s;
    }
	
	public static LocalDate getDueDate() {
		String DueDate = null;
		LocalDate Date = null;
		System.out.print("When Was The Assingment Due? (in yyyy-mm-dd format): ");
		
			while(!(input.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
				System.out.println("\tINVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
				System.out.print("When Was The Assingment Due? (in yyyy-mm-dd format): ");
				input.nextLine();
			}
			
			DueDate = input.nextLine();
			Date = StringToDate(DueDate);
			
			
			
			while(Date == null) {
				System.out.println("\tINVALID INPUT: Date was in correct format but was an invalid day.\n"
						+ " Please Enter A Valid Date in the format yyyy-mm-dd: ");
				while(!(input.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
					System.out.println("\tINVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
					System.out.print("When Was The Assingment Due? (in yyyy-mm-dd format): ");
					input.nextLine();
				}
				DueDate = input.nextLine();
				Date = StringToDate(DueDate);
			}
			
		return (Date);
	}
	
	public static LocalDate StringToDate(String input) {
		Scanner dateScanner = new Scanner(input);
		dateScanner.useDelimiter("-");
		int year = Integer.parseInt(dateScanner.next());
		int month = Integer.parseInt(dateScanner.next());
		int day = Integer.parseInt(dateScanner.next());
		
		
		
		switch(month) {
		case 2:
			if((year % 4) == 0 ) {
				if((year % 100) == 0) {
					if((year % 400) == 0) {										
						if(day > 29) {
							day = 0;
						} 
					}
					if(day > 28 && ((year % 400) != 0)) day = 0;
				} 
			} else if(day > 28) day = 0;
			break;
		case 4:
		case 6:
		case 9:
		case 11: 
			if(day > 30) day = 0;
			break;
		}
		dateScanner.close();
		if((year <= 0) || (month <= 0) || (month >= 13) || (day <= 0) || (day >= 32)) {
			return null;
		} else {
			return (LocalDate.of(year, month, day));
		}
	}
	
	
	public static double getAssinmentGrade() {
		String prompt = "What Was The Grade On The Assinment?\n"
				+ " (Stay in the range of 0.0 and 100.0): ";
		double number = 0.0;
		do {
			number = getdouble(prompt);
			if( (number < 0.000) || (number > 100.000) ) {
				System.out.println("\tINVALID INPUT: Correct Format But The Decimal Wasnt within the 0.0-100.0 range");
			}
		}while(number < 0.000 || number > 100.000);
		return number;
	}
	
	public static int[] getAssinmentGradeRange() {
		int[] numbers = {0,0};
		String prompt = "What Is minimum of the grade range?\n"
				+ " (Each choice must stay in the range of 0 and 100 and be whole numbers): ";
		do {
			numbers[0] = getInt(prompt);
			if( (numbers[0] < 0.000) || (numbers[0] > 100.000) ) {
				System.out.println("\tINVALID INPUT: Wasnt within the 0.0-100.0 range");
			}
		}while(numbers[0] < 0.000 || numbers[0] > 100.000);
		
		prompt = "What Is maximum of the grade range?\n"
				+ " (Each choice must stay in the range of 0 and 100 and be whole numbers): ";
		do {
			numbers[1] = getInt(prompt);
			if( (numbers[1] < 0.000) || (numbers[1] > 100.000) ) {
				System.out.println("\tINVALID INPUT: Wasnt within the 0.0-100.0 range");
			}
		}while(numbers[1] < 0.000 || numbers[1] > 100.000);
		
		return numbers;
	}
	
	public static ArrayList<LocalDate> getAssinmentDateRange() {
		String[] dates = {"",""};
		LocalDate Date = null;
		ArrayList<LocalDate> Dates = new ArrayList<>();
		System.out.print("What is the first Date for the range? (in yyyy-mm-dd format): ");
		while(!(input.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
			System.out.println("\tINVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
			System.out.print("What is the first Date for the range? (in yyyy-mm-dd format): ");
			input.nextLine();
		}
		dates[0] = input.nextLine();
		Date = StringToDate(dates[0]);
		while(Date == null) {
			System.out.println("\tINVALID INPUT: Date was in correct format but was an invalid day.\n"
					+ " Please Enter A Valid Date in the format yyyy-mm-dd: ");
			while(!(input.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
				System.out.println("\tINVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
				System.out.print("What is the first Date for the range? (in yyyy-mm-dd format):  (in yyyy-mm-dd format): ");
				input.nextLine();
			}
			dates[0] = input.nextLine();
			Date = StringToDate(dates[0]);
		}
		Dates.add(Date);
		
		
		System.out.print("What is the second Date for the range? (in yyyy-mm-dd format): ");
		while(!(input.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
			System.out.println("\tINVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
			System.out.print("What is the second Date for the range? (in yyyy-mm-dd format): ");
			input.nextLine();
		}
		dates[1] = input.nextLine();
		Date = StringToDate(dates[1]);
		while(Date == null) {
			System.out.println("\tINVALID INPUT: Date was in correct format but was an invalid day.\n"
					+ " Please Enter A Valid Date in the format yyyy-mm-dd: ");
			while(!(input.hasNext("\\d{4}-\\d{2}-\\d{2}"))) {
				System.out.println("\tINVALID INPUT: Please Enter A Valid Date in the format yyyy-mm-dd");
				System.out.print("What is the second Date for the range? (in yyyy-mm-dd format): (in yyyy-mm-dd format): ");
				input.nextLine();
			}
			dates[1] = input.nextLine();
			Date = StringToDate(dates[1]);
		}
		Dates.add(Date);
		return(Dates);
		
	}
	
	public static ArrayList<String> getGrade() {
		ArrayList<String> String1 = new ArrayList<>();
		String add;
		String gradeType = Console.getAssinmentType("What kind of Assingnment"
				+ " do you want to add? (discussion,program,quiz): ");
		String assigName = Console.getRequiredString("What Is The Assignment Name?: ");
		LocalDate DD = Console.getDueDate();
		double assinGrade = Console.getAssinmentGrade();
		char LetGrade = GradebookHelperFunctions.gradeLetterCalculate(assinGrade);
			String1.add(gradeType);
		switch(gradeType.toLowerCase()) {
		case "discussion":
			String assigRead = Console.getRequiredString("What Was The Assigned Reading?: ");
			add = assigName;
			add = add +"," + assinGrade;
			char[] temp = {'A'}; temp[0] = LetGrade;
			add = add +"," +temp[0];
			add = add +","+DD.toString();
			add = add + ","+assigRead;
			String1.add(add);
			break;
		case "quiz":
			int numQuestions = Console.getInt("How many Questions where on the quiz?: ");
			add = assigName;
			add = add +"," + assinGrade;
			char[] temp2 = {'A'}; temp2[0] = LetGrade;
			
			add = add +"," +("" +temp2[0]);
			add = add +","+DD.toString();
			add = add + ","+String.valueOf(numQuestions);
			String1.add(add);
			break;
		case "program":
			String Concept = Console.getRequiredString("What Is The Concept To Be Tested?: ");
			add = assigName;
			add = add +"," + assinGrade;
			char[] temp3 = {'A'}; temp3[0] = LetGrade;
			add = add +"," +temp3[0];
			add = add +","+DD.toString();
			add = add + ","+Concept;
			String1.add(add);
			break;
		}
		return(String1);
	}
}
