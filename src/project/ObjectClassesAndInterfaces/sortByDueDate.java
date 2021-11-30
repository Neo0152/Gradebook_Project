package project.ObjectClassesAndInterfaces;

import java.time.LocalDate;
import java.util.Comparator;

public class sortByDueDate implements Comparator<AssinmentInterface>{

	public int compare(AssinmentInterface X, AssinmentInterface Y) {
		LocalDate timeX = X.getDueDate();
		LocalDate timeY = Y.getDueDate();
		
		int timeCompare = timeX.compareTo(timeY);
		
		if(timeCompare == 0) {
			return 0;
		} else if(timeCompare > 0) {
			return 1;
		} else if(timeCompare < 0) {
			return -1;
		}
		return 0;
	}
}
