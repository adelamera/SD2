package project.spring.utils;

import java.sql.Date;
import java.util.Calendar;

public class DateComparator {

	public static boolean metDeadline(Date deadline) {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return deadline.after(date);

	}


}
