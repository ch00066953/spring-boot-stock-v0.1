package wlgtext;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import tool.DateUtil;

@Slf4j
public class DateTest {

	@Test
	public void date() throws ParseException {
		String year = "2018-03-12";
		String cyear = "2018-01-12";
		log.info(String.valueOf(DateUtil.compareDate(year, cyear)));
		
		String dateA = "2018/03/12";
		DateTime dtA = DateTime.parse(dateA);
		log.info(dtA.toString());
	}
}
