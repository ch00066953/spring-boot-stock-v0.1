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
import tool.StringFunction;

public class ToDo {
	Timer timer;
	boolean status = true;// 鏄惁鍙墽琛�
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
					MailTest.sendError("RATING閿欒淇℃伅锛�"+e.getMessage());
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
						message = "RATING FINISH 娆℃暟锛�"+i+"|鍓╀綑锛�"+cno+"|Rating鏃ユ湡锛�"+Para.getDate()+"|褰撳墠鏃堕棿"+StringFunction.getTodayNow()
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