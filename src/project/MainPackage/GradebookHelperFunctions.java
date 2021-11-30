package project.MainPackage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import project.Exceptions.GradebookEmptyException;
import project.Exceptions.GradebookFullException;
import project.Exceptions.InvalidGradeException;
import project.ObjectClassesAndInterfaces.*;
import project.Toolbox.DBToolbox;

public class GradebookHelperFunctions {

	public static void addToGradeBook(ArrayList<AssinmentInterface> IGB,ArrayList<String> Parts) throws GradebookFullException{
		switch(Parts.get(0)) {
		case "discussion":
			IGB.add(new Discussion(Parts.get(1)));
			break;
		case "quiz":
			IGB.add(new Quiz(Parts.get(1)));
			break;
		case "program":
			IGB.add(new Program(Parts.get(1)));
			break;
		}
	}
	
	public static void startGradeBook(ArrayList<AssinmentInterface> FGB,ArrayList<String> Parts){
		
		switch(Parts.get(0)) {
		case "discussion":
			FGB.add(new Discussion(Parts.get(1)));
			break;
		case "quiz":
			FGB.add(new Quiz(Parts.get(1)));
			break;
		case "program":
			FGB.add(new Program(Parts.get(1)));
			break;
		}
	}
	
	public static void removeGrade(ArrayList<AssinmentInterface> IGB,String assignName) throws GradebookEmptyException, InvalidGradeException{
		
		if(IGB.size() == 0) {
			throw new GradebookEmptyException("ERROR: The Gradebook Is Empty");
		} else if(IGB.size() > 0){
			boolean found = false;
			for(int i = 0; i<IGB.size();i++) {
				if((IGB.get(i).getAssignmentName().compareToIgnoreCase(assignName) == 0) && found != true) {
					IGB.remove(i);
					found = true;
				}
			}
			if(found == false) {
				throw new InvalidGradeException("ERROR: Assinment Could Not Be Found");
			} 
		}
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
	
	public static void sortList(ArrayList<AssinmentInterface> UNGB,int sortMethod) {
		
		switch(sortMethod) {
		case 1:
			Collections.sort(UNGB, new sortByName());
			break;
		case 2:
			Collections.sort(UNGB, new sortByPerGrade());
			break;
		case 3:
			Collections.sort(UNGB, new sortByLetGrade());
			break;
		case 4:
			Collections.sort(UNGB, new sortByDueDate());
			break;
		}
		
	}

	public static void printAssinment(AssinmentInterface Assinment) {
		String InfoString = null;
		if(Assinment instanceof Discussion) {
			InfoString = Assinment.toString();
		}
		if(Assinment instanceof Program) {
			InfoString = Assinment.toString();
		}
		if(Assinment instanceof Quiz) {
			InfoString = Assinment.toString();
		}
		
		Scanner printScanner = new Scanner(InfoString);
		printScanner.useDelimiter(",");
		
		System.out.print("--------------------------------------"
						  +"\nName: " + printScanner.next()
						  +"\nNumeric Grade:" + printScanner.next()
						  +"\nLetter Grade: " + printScanner.next()
						  +"\nDue Date: " + printScanner.next() + "\n"
						  );
		if(Assinment instanceof Discussion) {
			System.out.println("Assigned Reading: " + printScanner.next()
						  +"\n---------------------------------------");
		}
		if(Assinment instanceof Program) {
			System.out.println("Concept To Test: " + printScanner.next()
			  			  +"\n---------------------------------------");
		}
		if(Assinment instanceof Quiz) {
			System.out.println("Number Of Questions: " + printScanner.next()
			  			  +"\n---------------------------------------");
		}
		
		printScanner.close();
	}
	
	public static void printToFileNew(String FilePath,ArrayList<AssinmentInterface> GradesToOutput) throws IOException {
		PrintWriter toFile =  new PrintWriter( 
				  new BufferedWriter( 
				  new FileWriter(FilePath + ".txt")));
		
		for(AssinmentInterface X : GradesToOutput) {
			String InfoString = null;
			if(X instanceof Discussion) {
				InfoString = "Discussion,";
				InfoString = InfoString + X.toString();
			}
			if(X instanceof Program) {
				InfoString = "Program,";
				InfoString = InfoString + X.toString();
			}
			if(X instanceof Quiz) {
				InfoString = "Quiz,";
				InfoString = InfoString + X.toString();
			}
			
			System.out.println(InfoString);
			
			Scanner printScanner = new Scanner(InfoString);
			printScanner.useDelimiter(",");
			
			
			
			while(printScanner.hasNext()) {
				toFile.print(printScanner.next());
				if(printScanner.hasNext()) {
					toFile.print("\t");
				}
			}
			toFile.print("\n");
			
			printScanner.close();
		}
		toFile.close();
	}
	
	public static void printToFile(String FilePath,ArrayList<AssinmentInterface> GradesToOutput, boolean append) throws IOException {
		String Filetxt= FilePath + ".txt";
		
		PrintWriter toFile =  new PrintWriter( 
				  new BufferedWriter( 
				  new FileWriter(Filetxt)));
		
		for(AssinmentInterface X : GradesToOutput) {
			String InfoString = null;
			if(X instanceof Discussion) {
				InfoString = "Discussion,";
				InfoString = InfoString + X.toString();
			}
			if(X instanceof Program) {
				InfoString = "Program,";
				InfoString = InfoString + X.toString();
			}
			if(X instanceof Quiz) {
				InfoString = "Quiz,";
				InfoString = InfoString + X.toString();
			}
			
			Scanner printScanner = new Scanner(InfoString);
			printScanner.useDelimiter(",");
			
			while(printScanner.hasNext()) {
				toFile.print(printScanner.next());
				if(printScanner.hasNext()) {
					toFile.print("\t");
				}
			}
			toFile.print("\n");
			
			printScanner.close();
		}
		toFile.close();
	}
	
	public static File getGradeTextFolder() throws IOException {
		String GradeTextFilesPath = System.getProperty("user.dir") +"\\GradeTextFiles";
		Path GTFPath = Paths.get(GradeTextFilesPath);
		File returnGrade = (Paths.get(System.getProperty("user.dir"))).toFile();
		
		if(Files.notExists(GTFPath)) {
			Files.createDirectories(GTFPath);
			System.out.println("GradeTextFiles Directory Created");
			returnGrade = GTFPath.toFile();
		} else if(Files.exists(GTFPath)) {
			if(Files.isDirectory(GTFPath)) {
				System.out.println("GradeTextFiles Directory Loaded");
				returnGrade = GTFPath.toFile();
			} else {
				Files.createDirectories(GTFPath);
				System.out.println("GradeTextFiles Directory Created");
				returnGrade = GTFPath.toFile();
			}
		}
		return(returnGrade);
	}
	
	public static void setUpTable(Connection DB) throws SQLException {
			
			if(DBToolbox.checkTable(DB)) {
				return;
			} else {
				String SQLcreate =  "CREATE TABLE Gtable" +
						"(" +
						"ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
						"GradeType varchar(255)," +
						"Name varchar(255)," +
						"PerGrade varchar(255)," +
						"LetGrade varchar(255)," +
						"DueDate varchar(255)," +
						"AssinmentData varchar(255)" + 
						")";
					
				Statement STAM = DB.createStatement();
				STAM.executeUpdate(SQLcreate);
				return;
			}
	}
	
	
}
