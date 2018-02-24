package test;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.junit.Test;

import parameter.Para;
import stock.StockInfo1;
import tool.StringFunction;
import wlgtext.MailTest;

public class ToDoTest {

	@Test
	public void sendMail() {
		String ip = "unknow";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message = null;
		String avg = null;
		try {
			avg = StockInfo1.getTodayAvg().toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			message = "RATING FINISH 次数：？|剩余：？|Rating日期："+Para.getDate()+"|当前时间"+StringFunction.getTodayNow()
			+"|IP:"+ ip +"|avg:"+avg;
		MailTest.sendPrompt("RATING FINISH", message);
	}

}
