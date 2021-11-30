package project.Toolbox;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import project.Exceptions.GradebookEmptyException;
import project.Exceptions.GradebookFullException;
import project.Exceptions.InvalidGradeException;
import project.MainPackage.GradeBookfunctions;
import project.ObjectClassesAndInterfaces.AssinmentInterface;
import project.ObjectClassesAndInterfaces.Discussion;
import project.ObjectClassesAndInterfaces.Program;
import project.ObjectClassesAndInterfaces.Quiz;

public class JUnitTesting {

	private static ArrayList<AssinmentInterface> gradeBookExpected1;
	private static ArrayList<AssinmentInterface> gradeBookExpected2;
	
	@BeforeClass
	public static void setup() {
		gradeBookExpected1 = new ArrayList<>();
		gradeBookExpected1.add(new Quiz('A',99.0,(LocalDate.of(2001,01,14)),"name",1));
		gradeBookExpected1.add(new Program('A',99.0,(LocalDate.of(2001,01,14)),"name2","concpet"));
		gradeBookExpected1.add(new Discussion('A',99.0,(LocalDate.of(2001,01,14)),"name2","reading"));
		gradeBookExpected2 = new ArrayList<>();
		gradeBookExpected2.add(new Quiz('A',99.0,(LocalDate.of(2001,01,14)),"name",1));
		gradeBookExpected2.add(new Discussion('A',99.0,(LocalDate.of(2001,01,14)),"name2","reading"));
		
	}
	
	@Test
	public void testAdd() {
		try {
			ArrayList<AssinmentInterface>gradeBook = new ArrayList<>();
			ArrayList<String> parts1 = new ArrayList<>();
			parts1.add("quiz");
			parts1.add("name,99.0,A,2001-01-14,1");
			ArrayList<String> parts2 = new ArrayList<>();
			parts2.add("discussion");
			parts2.add("name2,99.0,A,2001-01-14,reading");
			GradeBookfunctions.F1(gradeBook,parts1);
			GradeBookfunctions.F1(gradeBook,parts2);
			Assertions.assertEquals(gradeBookExpected2,gradeBook);
		} catch (GradebookFullException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testremove() {
		try {
			GradeBookfunctions.F2(gradeBookExpected1,"name2");
		} catch (GradebookEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidGradeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertEquals(gradeBookExpected2,gradeBookExpected1);
	}
}
