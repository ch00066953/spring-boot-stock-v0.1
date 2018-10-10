package wlgtext;

import java.text.ParseException;
import java.util.Scanner;

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
	
	public static void main(String args[]) throws Exception
	{
		Scanner scanner = new Scanner(System.in);  
        while(scanner.hasNext()){  
            String str = scanner.next();  
            System.out.println(str);
            int value = scanner.nextInt(); 
            System.out.println(value);
        }
		/*int a=scanner.nextInt(),b=scanner.nextInt();
		System.out.println(a+b);*/
	}
}
