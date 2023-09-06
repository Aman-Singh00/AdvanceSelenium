package Java_Practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

public class SystemDate {

	@Test
	public void set() {
		
		
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		System.out.println(date);
		
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String required_Date = sdf.format(cal.getTime());
		
		System.out.println(required_Date);
		

	}

}
