package project.Toolbox;

public class MenuChoice {
	public static int gradeBookMenu() {
		int menuChoice = -1;
		
		System.out.println("\nPlease Pick A Program Function\n"
				         + "---------------------------------------------------------------\n"
				         + "1-Add A Grade In The Gradebook\n" 
				         + "2-Remove A Grade In The Gradebook\n"
				         + "3-Print All Of The Grades In The Gradebook\n"
				         + "4-Convert the Gradebook To A .txt File\n"
				         + "5-Add Grades To The Gradebook From A .txt File\n"
				         + "6-Add The Gradebook To the Database\n"
				         + "7-Add To The Gradebook From The Database\n"
				         + "8-Search the Database for Grades\n"
				         + "9-Quit The Program\n"
				         + "---------------------------------------------------------------\n");
		do {
			menuChoice = Console.getInt("Menu Choice: ");
			if((menuChoice < 1) || (menuChoice > 9)) {
				System.out.println("\tINVALID MENU CHOICE: Please Enter A Valid Menu Option: ");
			}
		}while((menuChoice < 1) || (menuChoice > 9));
		System.out.println();
		return(menuChoice);
	}
	
	public static int SortMenu() {
		int menuChoice = 0;
		
		System.out.println("How would you like to sort the list of grades?");
		System.out.println("--------------------------");
		System.out.println("1: Alphabetical name");
		System.out.println("2: Numeric grade");
		System.out.println("3: Letter grade");
		System.out.println("4: Due Date");
		System.out.println("--------------------------");
		do {
			menuChoice = Console.getInt("Menu Choice: ");
			if((menuChoice < 1) || (menuChoice > 4)) {
				System.out.println("\tINVALID MENU CHOICE: Please Enter A Valid Menu Option: ");
			}
		}while((menuChoice < 1) || (menuChoice > 4));
		System.out.println();
		return menuChoice;
	}
	
	public static int SQLsearch() {
		int menuChoice = 0;
		System.out.println("How would you like to search the Database for?");
		System.out.println("--------------------------");
		System.out.println("1: All Quiz Grades");
		System.out.println("2: All Program Grades");
		System.out.println("3: All Discussion Grades");
		System.out.println("4: All Grades Within A Certain Score Range(i.e., all grades with a score from 70 to 90)");
		System.out.println("5: All Grades Within A Certain Due Date range");
		System.out.println("6: All Grades With A Even Score");
		System.out.println("--------------------------");
		do {
			menuChoice = Console.getInt("Menu Choice: ");
			if((menuChoice < 1) || (menuChoice > 6)) {
				System.out.println("\tINVALID MENU CHOICE: Please Enter A Valid Menu Option: ");
			}
		}while((menuChoice < 1) || (menuChoice > 6));
		System.out.println();
		return menuChoice;
	}
}
