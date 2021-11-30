package project.Toolbox;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.ObjectClassesAndInterfaces.AssinmentInterface;
import project.ObjectClassesAndInterfaces.Discussion;
import project.ObjectClassesAndInterfaces.Program;
import project.ObjectClassesAndInterfaces.Quiz;

public class DBToolbox {
	  public static boolean checkTable(Connection DB) throws SQLException {
		  DatabaseMetaData DBMetaData = DB.getMetaData();
		  ResultSet DBMetaDataResult = DBMetaData.getTables(null, null, "Gtable", null);
		  if(DBMetaDataResult.next()) {
			  return true;
		  } else {
			  return false;
		  }
	  }
	  
	  public static ArrayList<AssinmentInterface> getGrades(Connection DB) throws SQLException{
		  ArrayList<AssinmentInterface> returnGrades = new ArrayList<>();
		  String SQL = "SELECT * FROM Gtable";
		  ResultSet DBGrades = DB.createStatement().executeQuery(SQL);
		  
		  while(DBGrades.next()) {
			  String GradeType = DBGrades.getString("GradeType");
			  String DBGrade = DBGrades.getString("Name");
			  DBGrade = DBGrade + "," + DBGrades.getString("PerGrade");
			  DBGrade = DBGrade + "," + DBGrades.getString("LetGrade");
			  DBGrade = DBGrade + "," + DBGrades.getString("DueDate");
			  DBGrade = DBGrade + "," + DBGrades.getString("AssinmentData");
			  
				switch(GradeType.toLowerCase()) {
				case "discussion":
					returnGrades.add(new Discussion(DBGrade));
					break;
				case "quiz":
					returnGrades.add(new Quiz(DBGrade));
					break;
				case "program":
					returnGrades.add(new Program(DBGrade));
					break;
				}
		  }
		  return(returnGrades);
	  }
	  
	  public static void addToGradeBook(ResultSet toAdd,ArrayList<AssinmentInterface> gradeBook) throws SQLException {
		  while(toAdd.next()) {
			  String GradeType = toAdd.getString("GradeType");
			  String DBGrade = toAdd.getString("Name");
			  DBGrade = DBGrade + "," + toAdd.getString("PerGrade");
			  DBGrade = DBGrade + "," + toAdd.getString("LetGrade");
			  DBGrade = DBGrade + "," + toAdd.getString("DueDate");
			  DBGrade = DBGrade + "," + toAdd.getString("AssinmentData");
			  
				switch(GradeType.toLowerCase()) {
				case "discussion":
					gradeBook.add(new Discussion(DBGrade));
					break;
				case "quiz":
					gradeBook.add(new Quiz(DBGrade));
					break;
				case "program":
					gradeBook.add(new Program(DBGrade));
					break;
				}
		  }
	  }

	public static void addToGradeBook(ArrayList<AssinmentInterface> gradesToAdd,
			ArrayList<AssinmentInterface> gradeBook) {
		for(AssinmentInterface X : gradesToAdd) {
			gradeBook.add(X);
		}
		
	}
}
