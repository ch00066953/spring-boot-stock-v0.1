package tool;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class DataConvert
{
  public static String toString(String s)
  {
    if (s == null) {
      return "";
    }
    return s;
  }
  
  public static String toString(String s, String sDefault)
  {
    if (s == null) {
      return sDefault;
    }
    return s;
  }
  
  public static String toHTMLString(String s)
  {
    if ((s == null) || (s.equals(""))) {
      return "&nbsp;";
    }
    return s;
  }
  
  public static String toHTMLMoney(String s)
  {
    if ((s == null) || (s.equals(""))) {
      return "&nbsp;";
    }
    return toMoney(s);
  }
  
  public static String toString(Short short1)
  {
    if (short1 == null) {
      return "";
    }
    return String.valueOf(short1);
  }
  
  public static String toString(Double double1)
  {
    if (double1 == null) {
      return "";
    }
    return String.valueOf(double1);
  }
  
  public static String toString(BigDecimal bigdecimal)
  {
    if (bigdecimal == null) {
      return "";
    }
    return String.valueOf(bigdecimal);
  }
  
  public static String toMoney(String s)
  {
    try
    {
      if ((s == null) || (s == "") || (s.equalsIgnoreCase("Null"))) {
        return "";
      }
      return toMoney(Double.valueOf(s).doubleValue());
    }
    catch (Exception e) {}
    return s;
  }
  
  public static String toMoney(Double double1)
  {
    if (double1 == null) {
      return "";
    }
    return toMoney(double1.doubleValue());
  }
  
  public static String toMoney(double d)
  {
    NumberFormat nf = NumberFormat.getInstance();
    
    nf.setMinimumFractionDigits(2);
    nf.setMaximumFractionDigits(2);
    
    return nf.format(d);
  }
  
  public static String toMoney(BigDecimal bigdecimal)
  {
    return toMoney(String.valueOf(bigdecimal));
  }
  
  public static String toDate_YMD(String s)
  {
    String s1 = s.substring(0, 4);
    String s2 = s.substring(4, 6);
    String s3 = s.substring(6, 8);
    String s4 = s1 + "年" + s2 + "月" + s3 + "日";
    return s4;
  }
  
  public static String toDate_YMD2(String s, String s1)
  {
    String s2 = s.substring(0, 4);
    String s3 = s1.substring(0, 4);
    String s4 = s.substring(4, 6);
    String s5 = s1.substring(4, 6);
    String s6 = s.substring(6, 8);
    String s7 = s1.substring(6, 8);
    String s8 = s2 + "年" + s4 + "月" + s6 + "日至" + s3 + "年" + s5 + "月" + s7 + "日";
    return s8;
  }
  
  public static String toDate_YM(String s)
  {
    String s1 = s.substring(0, 4);
    String s2 = s.substring(4, 6);
    String s3 = s1 + "年" + s2 + "月";
    return s3;
  }
  
  public static String toDate_Y(String s)
  {
    String s1 = s.substring(0, 4);
    String s2 = s1 + "年";
    return s2;
  }
  
  public static String toDate_YMD0(String s)
  {
    String s1 = s.substring(0, 4);
    String s2 = s.substring(4, 6);
    String s3 = s.substring(6, 8);
    String s4 = "-";
    String s5 = s1 + s4 + s2 + s4 + s3;
    return s5;
  }
  
  public static String toDate_YM2(String s)
  {
    String s1 = s.substring(0, 4);
    String s2 = s.substring(4, 6);
    String s3 = s1 + "年" + s2 + "月";
    return s3;
  }
  
  public static String toRealString_old(String s)
  {
    try
    {
      String s1 = s;
      if (s1 != null) {}
      return new String(s1.getBytes(), "ISO8859_1");
    }
    catch (UnsupportedEncodingException unsupportedencodingexception) {}
    return s;
  }
  
  public static String toRealString(String s)
  {
    return toRealString(iChange, s);
  }
  
  public static String toRealString(int i, String s)
  {
    if (s == null) {
      return null;
    }
    try
    {
      String s1 = s;
      if (i == 1) {
        return s1;
      }
      if (s1 != null)
      {
        if (i == 0) {
          s1 = new String(s1.getBytes(), "ISO8859_1");
        }
        if (i == 2) {
          s1 = new String(s1.getBytes("ISO8859_1"), "GBK");
        }
        if (i == 3) {
          s1 = new String(s1.getBytes("GBK"), "ISO8859_1");
        }
        if (i != 5) {}
      }
      return decode(s1, "GBK");
    }
    catch (UnsupportedEncodingException unsupportedencodingexception)
    {
      return s;
    }
    catch (Exception e) {}
    return s;
  }
  
  public static String decode(String s, String encoding)
    throws Exception
  {
    if (s == null) {
      return s;
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++)
    {
      char c = s.charAt(i);
      switch (c)
      {
      case '+': 
        sb.append(' ');
        break;
      case '%': 
        try
        {
          sb.append((char)Integer.parseInt(s.substring(i + 1, i + 3), 16));
        }
        catch (NumberFormatException e)
        {
          throw new IllegalArgumentException();
        }
        i += 2;
        break;
      default: 
        sb.append(c);
      }
    }
    String result = sb.toString();
    byte[] inputBytes = result.getBytes("8859_1");
    return new String(inputBytes, encoding);
  }
  
  public static int iChange = 1;
  
  public static double toDouble(String s)
  {
    if ((s == null) || (s.equals(""))) {
      return 0.0D;
    }
    return Double.parseDouble(s);
  }
  
  public static int toInt(String s)
  {
    if ((s == null) || (s.equals(""))) {
      return 0;
    }
    return Integer.parseInt(s);
  }
}
