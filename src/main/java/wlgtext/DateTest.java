package wlgtext;

import java.text.ParseException;

import org.junit.Test;

import tool.DateUtil;


public class DateTest {

	@Test
	public void date() throws ParseException {
		
		DateUtil du = new DateUtil();
		System.out.println(du.getDateOfWeek("2014/07/12"));
	}
}
