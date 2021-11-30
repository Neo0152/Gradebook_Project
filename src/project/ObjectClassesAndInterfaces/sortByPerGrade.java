package project.ObjectClassesAndInterfaces;

import java.util.Comparator;

public class sortByPerGrade implements Comparator<AssinmentInterface> {
	public int compare(AssinmentInterface X, AssinmentInterface Y) {
		
		if(X.getPercentGrade() > Y.getPercentGrade()) {
			return -1;
		} else if (X.getPercentGrade() == Y.getPercentGrade()) {
			return 0;
		} else if (X.getPercentGrade() < Y.getPercentGrade()) {
			return 1;
		}
		
		return 0; 
	}
}
