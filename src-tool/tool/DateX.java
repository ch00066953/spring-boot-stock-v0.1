package tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateX
{
  public static final int YEAR = 0;
  public static final int YE = 0;
  public static final int MONTH = 1;
  public static final int MO = 1;
  public static final int DATE = 2;
  public static final int DA = 2;
  public static final int HOUR = 3;
  public static final int HO = 3;
  public static final int MINUTE = 4;
  public static final int MI = 4;
  public static final int SECOND = 5;
  public static final int SE = 5;
  public static final int MILLISECOND = 6;
  public static final int MS = 6;
  private Date date = null;
  private int[] ymd = null;
  private Calendar calendar = null;
  
  public DateX(Date date)
  {
    this.date = date;
    this.calendar = Calendar.getInstance();
    this.calendar.setTime(date);
    setYmd();
  }
  
  public DateX(String date)
  {
    this(StringX.parseDate(date));
  }
  
  public DateX()
  {
    this(new Date());
  }
  
  public DateX(int y, int m, int d)
  {
    this.calendar = Calendar.getInstance();
    this.calendar.clear();
    this.calendar.set(y, m, d);
    this.date = this.calendar.getTime();
    setYmd();
  }
  
  private void setYmd()
  {
    this.ymd = new int[7];
    this.ymd[0] = this.calendar.get(1);
    this.ymd[1] = (this.calendar.get(2) + 1);
    this.ymd[2] = this.calendar.get(5);
    this.ymd[3] = this.calendar.get(10);
    this.ymd[4] = this.calendar.get(12);
    this.ymd[5] = this.calendar.get(13);
    this.ymd[6] = this.calendar.get(14);
  }
  
  public int getFieldt(int field)
  {
    return this.ymd[field];
  }
  
  public int getYear()
  {
    return this.ymd[0];
  }
  
  public int getMonth()
  {
    return this.ymd[1];
  }
  
  public int getDate()
  {
    return this.ymd[2];
  }
  
  public int getHour()
  {
    return this.ymd[3];
  }
  
  public int getMinute()
  {
    return this.ymd[4];
  }
  
  public int getSecond()
  {
    return this.ymd[5];
  }
  
  public int getMillisecond()
  {
    return this.ymd[6];
  }
  
  public int[] ymd7()
  {
    return (int[])this.ymd.clone();
  }
  
  public boolean isEndOfYear()
  {
    return this.ymd[1] == 12;
  }
  
  public boolean isEndOfMonth()
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(this.date);
    cal.add(5, 1);
    return cal.get(5) == 1;
  }
  
  public boolean isLeapYear()
  {
    return this.ymd[0] % 400 == 0;
  }
  
  public Date getAdjustRelativeDate(int field, int number, boolean expendMonthEnd)
  {
    if (number == 0) {
      return this.date;
    }
    if ((field < 0) || (field > 6)) {
      return null;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(this.date);
    cal.add(getCalendarConst(field), number);
    if ((expendMonthEnd) && (isEndOfMonth()))
    {
      int m = cal.get(2);
      cal.set(cal.get(1), m + 1, 1);
      while (cal.get(2) != m) {
        cal.add(5, -1);
      }
    }
    return cal.getTime();
  }
  
  public Date getAbsoluteRelativeDate(int field, int number)
  {
    if (number == 0) {
      return this.date;
    }
    if ((field < 0) || (field > 6)) {
      return null;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(this.date);
    if (field == 1) {
      cal.set(getCalendarConst(field), this.ymd[1] + number - 1);
    } else {
      cal.set(getCalendarConst(field), this.ymd[field] + number);
    }
    return cal.getTime();
  }
  
  private int getCalendarConst(int dc)
  {
    if (dc == 0) {
      return 1;
    }
    if (dc == 1) {
      return 2;
    }
    if (dc == 2) {
      return 5;
    }
    if (dc == 3) {
      return 11;
    }
    if (dc == 4) {
      return 12;
    }
    if (dc == 5) {
      return 13;
    }
    if (dc == 6) {
      return 14;
    }
    return dc;
  }
  
  public Date getTime()
  {
    return this.date;
  }
  
  public String getDateString()
  {
    return getDateString("yyyy/MM/dd");
  }
  
  public String getDateString(String format)
  {
    return new SimpleDateFormat(format).format(this.date);
  }
  
  public static String format(Date date)
  {
    return format(date, "yyyy/MM/dd");
  }
  
  public static String format(Date date, String format)
  {
    return new SimpleDateFormat(format).format(date);
  }
}
