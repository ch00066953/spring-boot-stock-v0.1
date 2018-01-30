package wlgtext.jodatime;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import tool.DateUtils;

public class Jodademo {

	public void demo1() {
		Date d = DateUtils.parseDate("2014/1/6");
		System.out.println(d);
		DateTime dateTime = new DateTime(d);
		System.out.println(dateTime);
		LocalDate localDate = new LocalDate(d);
		System.out.println(localDate);
		LocalDate localDate1 = new LocalDate();
		System.out.println(localDate1);
		LocalDate localDate2 = new LocalDate("2014/11/06");
		System.out.println(localDate2);
	}
	
	public static void main(String[] args) {
		Jodademo jd = new Jodademo();
		jd.demo1();
	}
}
