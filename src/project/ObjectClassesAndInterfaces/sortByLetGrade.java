package project.ObjectClassesAndInterfaces;

import java.util.Comparator;

public class sortByLetGrade implements Comparator<AssinmentInterface> {
	
	public int compare(AssinmentInterface X, AssinmentInterface Y) {
		
		String letGradeX = X.getLetterGrade() + "";
		String letGradeY = Y.getLetterGrade() + "";
		
		int letGradeCompare = letGradeX.compareTo(letGradeY);
		
		int nameCompare = X.getAssignmentName().compareTo(Y.getAssignmentName());
		
		return (letGradeCompare == 0) ? nameCompare : letGradeCompare ;
	}
}
