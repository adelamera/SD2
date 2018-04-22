package project.spring.utils;

import java.sql.Date;
import java.util.Calendar;

public class DateComparator {

	public static boolean metDeadline(Date deadline) {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		String[] now = date.toString().split("-");
		String[] deadlines = deadline.toString().split("-");
		if (Integer.valueOf(deadlines[0]) < Integer.valueOf(now[0])) {
			return false;
		} else if (Integer.valueOf(deadlines[1]) < Integer.valueOf(now[1])) {
			return false;
		} else if ((Integer.valueOf(deadlines[2]) < Integer.valueOf(now[2]))
				&& (Integer.valueOf(deadlines[1]) == Integer.valueOf(now[1]))) {
			return false;
		}
		return true;

	}

}
