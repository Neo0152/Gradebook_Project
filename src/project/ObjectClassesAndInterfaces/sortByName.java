package project.ObjectClassesAndInterfaces;

import java.util.Comparator;

public class sortByName implements Comparator<AssinmentInterface> {
	
	public int compare(AssinmentInterface X, AssinmentInterface Y) {
		
		int nameCompare = X.getAssignmentName().compareTo(Y.getAssignmentName());
		
		return nameCompare;
	}
}