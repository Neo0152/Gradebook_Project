package project.MainPackage;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import project.Exceptions.GradebookEmptyException;
import project.Exceptions.GradebookFullException;
import project.Exceptions.InvalidGradeException;
import project.ObjectClassesAndInterfaces.AssinmentInterface;
import project.ObjectClassesAndInterfaces.Discussion;
import project.ObjectClassesAndInterfaces.Program;
import project.ObjectClassesAndInterfaces.Quiz;
import project.Toolbox.Console;
import project.Toolbox.DBToolbox;
import project.Toolbox.MenuChoice;

public class GradeBookfunctions {

	public static ArrayList<AssinmentInterface> F1(ArrayList<AssinmentInterface> gradeBook,ArrayList<String> Parts) throws GradebookFullException {
		if(gradeBook.size() == 0) {
			GradebookHelperFunctions.startGradeBook(gradeBook,Parts);
			return gradeBook;
		} else if(gradeBook.size() > 0) {
			GradebookHelperFunctions.addToGradeBook(gradeBook,Parts);
			return gradeBook;
		}
		return gradeBook;
	}
	
	public static void F2(ArrayList<AssinmentInterface> gradeBook,String remove) throws GradebookEmptyException, InvalidGradeException {
		GradebookHelperFunctions.removeGrade(gradeBook,remove);
	}
	
	public static void F3(ArrayList<AssinmentInterface> gradeBook) throws GradebookEmptyException {
		if(gradeBook.size() == 0) {
			throw new GradebookEmptyException("ERROR: The Gradebook Is Empty");
		}
		
		int subMenufor3 = MenuChoice.SortMenu();
		
		GradebookHelperFunctions.sortList(gradeBook,subMenufor3);
		
		for(int i = 0; i < gradeBook.size();i++) {
			AssinmentInterface X = gradeBook.get(i);
			GradebookHelperFunctions.printAssinment(X);
		}
	}
	
	public static Path F4(ArrayList<AssinmentInterface> gradeBook, boolean fileMade,Path path) throws IOException, GradebookEmptyException {
		if(gradeBook.size() == 0) {
			throw new GradebookEmptyException("ERROR: The Gradebook Is Empty");
		}
		
		File GradeTextFiles = GradebookHelperFunctions.getGradeTextFolder();
		boolean nameTaken;
		Random nameGenerator = new Random();
		int randomNumberCap = 1000;
		int fileCount = 0;
		int randomMin = 0;
		
		if(fileMade == true) {
			GradebookHelperFunctions.printToFile(path.toString(),gradeBook,true);
			return(path);
		} else {
			do {
				if(fileCount == randomNumberCap) {
					randomNumberCap = randomNumberCap * 2;
					randomMin = fileCount;
				}
				nameTaken = false;
				int GradeKey = nameGenerator.nextInt(randomNumberCap - randomMin + 1) + randomMin;
				path = Paths.get(GradeTextFiles.getPath() + "//GradeBook" + Integer.toString(GradeKey));
				if(Files.exists(path)) {
					nameTaken = true;
					fileCount++;
				}
			}while(nameTaken == true);
		}	
		GradebookHelperFunctions.printToFileNew(path.toString(),gradeBook);					
		System.out.println("Grades saved");	
		return(path);
	}
	
	public static void F5(ArrayList<AssinmentInterface> gradeBook) throws IOException {
		
		 File GradebookDirectory = GradebookHelperFunctions.getGradeTextFolder();
		 Path GradebookPath = GradebookDirectory.toPath();
		 DirectoryStream<Path> GradeBooks = Files.newDirectoryStream(GradebookPath);
		 File[] Gradebooktest = GradebookDirectory.listFiles();
		 
		 System.out.println("Gradebood Files Directory:" + GradebookDirectory.getAbsolutePath());
		 System.out.println("Files:");
		 
		 for(Path X : GradeBooks) {
			 if (Files.isRegularFile(X)) { 
		            System.out.println(X.getFileName()); 
		        } 
		 }
		 
		 System.out.println();
		 String ChosenFile = Console.getRequiredString("*include the .txt and be case sensitive. To load all files Enter:All.txt*\nPlease Choose a gradebook to load: ");
		 System.out.print("Loading File...");
		 
		 if(ChosenFile.compareToIgnoreCase("All.txt") == 0) {
			 for(File X : Gradebooktest) {								 
				 BufferedReader FileInput = new BufferedReader(new FileReader(X));
				
				 String readLine;			
				 
				 while((readLine = FileInput.readLine()) != null) {
					 Scanner StringScanner = new Scanner(readLine);
					 StringScanner.useDelimiter("\t");
					 String GradeType = StringScanner.next();
					 String GradeName = StringScanner.next();
					 String GradePercent = StringScanner.next();
					 String GradeLetter = StringScanner.next();
					 String GradeDueDate = StringScanner.next();
					 switch(GradeType.toLowerCase()) {
						case "discussion":
							String GradeReading = StringScanner.next();
							 
							gradeBook.add(new Discussion((GradeLetter.toCharArray())[0],(Double.valueOf(GradePercent)),
									Console.StringToDate(GradeDueDate),GradeName,GradeReading));
							break;
						case "quiz":
							String GradeNumQues = StringScanner.next();
							gradeBook.add(new Quiz((GradeLetter.toCharArray())[0],(Double.valueOf(GradePercent)),
									Console.StringToDate(GradeDueDate),GradeName,Integer.valueOf(GradeNumQues)));
							break;
						case "program":
							String GradeConcept = StringScanner.next();
							gradeBook.add(new Program((GradeLetter.toCharArray())[0],(Double.valueOf(GradePercent)),
									Console.StringToDate(GradeDueDate),GradeName,GradeConcept));
							break;
						}
					 StringScanner.close();
				 }
				 FileInput.close();
				 System.out.println("Loaded");
			 }
		 } else {
			 boolean notValid = true;
			 
			 do {
				 for(File X : Gradebooktest) {
					Path XPath = X.toPath();
					String FileName = XPath.getFileName().toString();
					if(FileName.compareTo(ChosenFile) == 0) {
						notValid = false;
						System.out.println("File Loaded!");
					}
				 }
				 if(notValid == true) {
					 System.out.print("Failed > File does not exist\n\n");
					 ChosenFile= Console.getRequiredString("*include the .txt and be case sensitive*\n"
					 		+ "Enter in a valid Filename: ");
					 System.out.print("Loading File...");
				 }
			 }while(notValid == true);
			 
			 File ToLoadGradeBook = (Paths.get(GradebookPath.toString() + "\\" + ChosenFile)).toFile();
			 BufferedReader FileInput = new BufferedReader(new FileReader(ToLoadGradeBook));
			 String readLine;			
			 
			 while((readLine = FileInput.readLine()) != null) {
				 Scanner StringScanner = new Scanner(readLine);
				 StringScanner.useDelimiter("\t");
				 String GradeType = StringScanner.next();
				 String GradeName = StringScanner.next();
				 String GradePercent = StringScanner.next();
				 String GradeLetter = StringScanner.next();
				 String GradeDueDate = StringScanner.next();
				 switch(GradeType.toLowerCase()) {
					case "discussion":
						String GradeReading = StringScanner.next();
						 
						gradeBook.add(new Discussion((GradeLetter.toCharArray())[0],(Double.valueOf(GradePercent)),
								Console.StringToDate(GradeDueDate),GradeName,GradeReading));
						break;
					case "quiz":
						String GradeNumQues = StringScanner.next();
						gradeBook.add(new Quiz((GradeLetter.toCharArray())[0],(Double.valueOf(GradePercent)),
								Console.StringToDate(GradeDueDate),GradeName,Integer.valueOf(GradeNumQues)));
						break;
					case "program":
						String GradeConcept = StringScanner.next();
						gradeBook.add(new Program((GradeLetter.toCharArray())[0],(Double.valueOf(GradePercent)),
								Console.StringToDate(GradeDueDate),GradeName,GradeConcept));
						break;
					}
				 StringScanner.close();
			 }
			 FileInput.close();
		 }
	}
	
	public static void F6(ArrayList<AssinmentInterface> gradeBook,Connection DB) throws SQLException {
		try {
			GradebookHelperFunctions.setUpTable(DB);
			
			for(AssinmentInterface X : gradeBook) {
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
				
				ArrayList<String> XParts = new ArrayList<>();
				Scanner printScanner = new Scanner(InfoString);
				printScanner.useDelimiter(",");
				
				while(printScanner.hasNext()) {
					XParts.add(printScanner.next());
				}
				printScanner.close();
				
				String SQL = "INSERT INTO Gtable" 
							+"(GradeType,Name,PerGrade,LetGrade,DueDate,AssinmentData)"
							+"VALUES"
							+"(?,?,?,?,?,?)";
				
				PreparedStatement PS = DB.prepareStatement(SQL);
				PS.setString(1,XParts.get(0));
				PS.setString(2,XParts.get(1));
				PS.setString(3,XParts.get(2));
				PS.setString(4,XParts.get(3));
				PS.setString(5,XParts.get(4));
				PS.setString(6,XParts.get(5));
				PS.executeUpdate();
			}
		} catch (SQLException e) {
			try {
				DB.close();
			} catch (SQLException x) {
				x.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static void F7(ArrayList<AssinmentInterface> gradeBook,Connection DB) {
		try {
			
			if(DBToolbox.checkTable(DB)) {
				ArrayList<AssinmentInterface> NewGradebook = new ArrayList<>();
				ArrayList<AssinmentInterface> DBGrades = new ArrayList<>(DBToolbox.getGrades(DB));
				
				if(DBGrades.size() != 0) {
					for(int i = 0;i<DBGrades.size();i++) {
						boolean INGB = false;
						for(int c = 0;c<gradeBook.size();c++) {
							if(((DBGrades.get(i).toString()).compareToIgnoreCase(gradeBook.get(c).toString()) == 0) && DBGrades.get(i).getPercentGrade() == gradeBook.get(c).getPercentGrade()) {
								INGB = true;
								break;
							}
						}
						if(INGB == false) {
							NewGradebook.add(DBGrades.get(i));
						}
					}
					for(AssinmentInterface X : NewGradebook) {
						gradeBook.add(X);
					}
					System.out.println("Added remaining grades in Gtable that were not in the current gradebook");
					return;
				} else {
					System.out.println("Gtable is empty");
					return;
				}				
			} else {
				System.out.println("Gtable does not exist");
				return;
			}
		} catch (SQLException e) {
			try {
				DB.close();
			} catch (SQLException x) {
				x.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static void F8(ArrayList<AssinmentInterface> gradeBook,Connection DB) {
		try {
			if(DBToolbox.checkTable(DB)) {
				if((DBToolbox.getGrades(DB)).size() < 1) {
					System.out.println("Gtable is empty");
				} else {
					int menuChoice = MenuChoice.SQLsearch();
					switch(menuChoice) {
					case 1:
						String SQL1 = "SELECT * FROM Gtable WHERE GradeType = 'Quiz'";
						PreparedStatement PS1 = DB.prepareStatement(SQL1);
						DBToolbox.addToGradeBook(PS1.executeQuery(), gradeBook);
						break;
					case 2:
						String SQL2 = "SELECT * FROM Gtable WHERE GradeType = 'Program'";
						PreparedStatement PS2 = DB.prepareStatement(SQL2);
						DBToolbox.addToGradeBook(PS2.executeQuery(), gradeBook);
						break;
					case 3:
						String SQL3 = "SELECT * FROM Gtable WHERE GradeType = 'Discussion'";
						PreparedStatement PS3 = DB.prepareStatement(SQL3);
						DBToolbox.addToGradeBook(PS3.executeQuery(), gradeBook);
						break;
					case 4:
						int[] rangeInfo = Console.getAssinmentGradeRange();
						String SQL4 = "SELECT * FROM Gtable WHERE PerGrade BEtWEEN ? AND ?";
						PreparedStatement PS4 = DB.prepareStatement(SQL4);
						PS4.setString(1,(String.valueOf(rangeInfo[0])));
						PS4.setString(2,(String.valueOf(rangeInfo[1])));
						DBToolbox.addToGradeBook(PS4.executeQuery(), gradeBook);
						break;
					case 5:
						ArrayList<LocalDate> Dates = Console.getAssinmentDateRange();
						ArrayList<AssinmentInterface> grades = DBToolbox.getGrades(DB);
						ArrayList<AssinmentInterface> gradesToAdd = new ArrayList<>();
						for(AssinmentInterface X : grades) {
							if((X.getDueDate().isAfter(Dates.get(0)) || (X.getDueDate().isEqual(Dates.get(0))) )
									&& X.getDueDate().isBefore(Dates.get(1)) || X.getDueDate().isEqual(Dates.get(1))) {
								gradesToAdd.add(X);
							}
						}
						
						DBToolbox.addToGradeBook(gradesToAdd, gradeBook);
						break;
					case 6:
						ArrayList<AssinmentInterface> grades2 = DBToolbox.getGrades(DB);
						ArrayList<AssinmentInterface> gradesToAdd2 = new ArrayList<>();
						for(AssinmentInterface X : grades2) {
							if(((int)(X.getPercentGrade())) % 2 == 0) {
								gradesToAdd2.add(X);
							}
						}
						
						DBToolbox.addToGradeBook(gradesToAdd2, gradeBook);
						break;
					}
				}
			} else {
				System.out.println("Gtable does not exist");
				return;
			}
		} catch (SQLException e) {
			try {
				DB.close();
			} catch (SQLException x) {
				x.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
