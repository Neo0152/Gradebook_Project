/*
 * Assignment: Project part 1
 * Name: Neil Kuehn
 */


package project.MainPackage;

import project.ObjectClassesAndInterfaces.*;
import project.Exceptions.*;
import project.Toolbox.*;
import project.Toolbox.Console;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Gradebook {

	public static void main(String[] args) throws GradebookEmptyException, InvalidGradeException {
			int menuChoice = 0;
			Scanner inputScanner = new Scanner(System.in);
			Boolean runGradeBook = true;
			
			System.out.println("Welcome to the Gradebook Program\n"
					+ "This program was designed by Neil Kuehn");
		
			ArrayList<AssinmentInterface> gradeBook = new ArrayList<>();
			Path currentGradeTextName = null;
			boolean fileMade = false;
			
			do {
				try {
					menuChoice = MenuChoice.gradeBookMenu();
					switch(menuChoice) {
						
					case 1: 
						ArrayList<String> part = Console.getGrade();
						GradeBookfunctions.F1(gradeBook,part);
						break;
					case 2:
						GradeBookfunctions.F2(gradeBook,Console.getRequiredString(
								"What assignment would you like to delete?: "));
						break;
					case 3:
						GradeBookfunctions.F3(gradeBook);
						break;
					case 4:
						currentGradeTextName = GradeBookfunctions.F4(gradeBook,fileMade,currentGradeTextName);
						fileMade = true;
						break;
					case 5:
						GradeBookfunctions.F5(gradeBook);
						break;
					case 6:
						GradeBookfunctions.F6(gradeBook,DBconnecter.getConnection(menuChoice));
						System.out.print("Gradebook Added to the Database");
						break;
					case 7:
						GradeBookfunctions.F7(gradeBook,DBconnecter.getConnection(menuChoice));
						break;
					case 8:
						GradeBookfunctions.F8(gradeBook,DBconnecter.getConnection(menuChoice));
						break;
					case 9:
						runGradeBook = false;
						Connection DB = DBconnecter.getConnection(menuChoice);
						if(DB != null) {
							DB.close();
							System.out.print("Connection to Database closed. ");
						}
						System.out.println("Goodbye!");
						break;
					}
				}  catch(GradebookEmptyException e) {
					System.out.println(e);
				} catch (GradebookFullException e) {
					System.out.println( e );
				}  catch(InvalidGradeException e) {
					System.out.println( e );
				} catch (IOException e) {
					System.out.println(e);
				} catch (SQLException e) {
					System.out.println(e);
				}
			}while(runGradeBook);
			inputScanner.close();
	}
}
	

