package wlgtext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import parameter.Para;

import moving.business.AngleMoving;

import rating.RunRating;
import stock.StockInfo1;
import tool.DateUtil;
import tool.StringFunction;

public class ToDo {
	Timer timer;
	boolean status = true;// 是否可执行
	private static ToDo uniqueInstance = null;

	private ToDo() {
		
	}

	public static ToDo getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ToDo();
		}
		return uniqueInstance;
	}

	public void run(int seconds) {
		timer = new Timer();
		int time = seconds * 1000 * 60;
//		timer.schedule(new ToDoTask(), time * 12 ,time);
		timer.schedule(new ToDoTask(), 0, time);
	}

	public void cancel() {
		timer.cancel();
	}

	class ToDoTask extends TimerTask {
		int i = 0;

		public void run() {
			if (status) {
				i++;
				status = false;
				System.out.println("OK, It's time to do something!");
				System.out.println(i);
				RunRating rr = new RunRating();
				int cno = 0;
				try {
					cno = rr.checkexcuse();
				} catch (SQLException e) {
					MailTest.sendError("RATING错误信息！"+e.getMessage());
					e.printStackTrace();
				}
				if (i == 5 || cno == 0) {
//					AngleMoving am = new AngleMoving();
//					am.excuse();
					timer.cancel(); // Terminate the thread
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
						message = "RATING FINISH 次数："+i+"|剩余："+cno+"|Rating日期："+Para.getDate()+"|当前时间"+DateUtil.getNowTime()
						+"|IP:"+ ip +"|avg:"+avg;
					MailTest.sendPrompt("RATING FINISH", message);
				}
				status = true;
			}
		}
	}

	public static void main(String args[]) {
		int time = 30;
		System.out.println("Schedule something to do in 5 seconds.");
		new ToDo().run(time);
		System.out.println("Waiting.");
	}
}