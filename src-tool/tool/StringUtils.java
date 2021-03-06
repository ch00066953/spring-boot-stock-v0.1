package tool;


import java.io.UnsupportedEncodingException;

public class StringUtils
{
  public static String replace(String s, String s1, String s2)
  {
    for (int i = s.indexOf(s1, 0); i != -1; i = s.indexOf(s1, i + s2.length())) {
      s = s.substring(0, i) + s2 + s.substring(i + s1.length());
    }
    return s;
  }
  
  public static String getSeparate(String s, String s1, int i)
  {
    int k = 0;
    int l = 0;
    s = s + s1;
    int j;
    while ((j = s.indexOf(s1, k)) >= 0)
    {
      l++;
      if (l == i) {
        return s.substring(k, j).trim();
      }
      k = j + 1;
    }
    return "";
  }
  
  public static int getSeparateSum(String s, String s1)
  { 
    int i = 0;
    int j = 0;
    for (; (i = s.indexOf(s1, i) + 1) > 0; j++) {
    }
    return j + 1;
  }
  
  public static String[] toStringArray(String s, String s1)
  {
    String[] as = null;
    int i = getSeparateSum(s, s1);
    if (i > 0)
    {
      as = new String[i];
      for (int j = 1; j <= i; j++) {
        as[(j - 1)] = getSeparate(s, s1, j);
      }
    }
    return as;
  }
  
  public static int[] parseStrArray2Int(String[] s)
  {
    int i = s.length;
    int[] i1 = new int[i];
    for (int j = 0; j < i; j++) {
      if ((s[j] == null) || (s[j].length() == 0)) {
        i1[j] = 0;
      } else {
        i1[j] = Integer.parseInt(s[j]);
      }
    }
    return i1;
  }
  
  public static String[] parseIntArray2Str(int[] is)
  {
    String[] as = new String[is.length];
    for (int i = 0; i < is.length; i++) {
      as[i] = String.valueOf(is[i]);
    }
    return as;
  }
  
  public static int getMaxFromIntArray(int[] ai)
  {
    int i = 0;
    for (int j = 0; j < ai.length; j++) {
      if (i < ai[j]) {
        i = ai[j];
      }
    }
    return i;
  }
  
  public static String getStringValue(String s, String s1, String s2, int chartset)
  {
    if ((s == null) || (s.length() == 0)) {
      return s2.trim();
    }
    return convertCodeset(s1.trim(), chartset);
  }
  
  public static String getStringValue(String s, String s1, String s2)
  {
    return getStringValue(s, s1, s2, -1);
  }
  
  public static String getStringValue(String s, String s2, int chartset)
  {
    return getStringValue(s, s, s2, chartset);
  }
  
  public static String getStringValue(String s, String s2)
  {
    return getStringValue(s, s2, -1);
  }
  
  public static String parseExcelColIndex(String sIndex)
  {
    return parseExcelColIndex(Integer.parseInt(sIndex));
  }
  
  public static String parseExcelColIndex(int iIndex)
  {
    String sExcelCol = "";
    if (iIndex > 0)
    {
      String[][] sCharacter = { { "1", "a" }, { "2", "b" }, { "3", "c" }, { "4", "d" }, { "5", "e" }, { "6", "f" }, { "7", "g" }, { "8", "h" }, { "9", "i" }, { "10", "j" }, { "11", "k" }, { "12", "l" }, { "13", "m" }, { "14", "n" }, { "15", "o" }, { "16", "p" }, { "17", "q" }, { "18", "r" }, { "19", "s" }, { "20", "t" }, { "21", "u" }, { "22", "v" }, { "23", "w" }, { "24", "x" }, { "25", "y" }, { "26", "z" } };
      if (iIndex > 26) {
        sExcelCol = sCharacter[(iIndex / 26 - 1)][1] + sCharacter[(iIndex % 26 - 1)][1];
      } else {
        sExcelCol = sCharacter[(iIndex - 1)][1];
      }
    }
    return sExcelCol;
  }
  public static String convertCodeset(String s, int i)
  {
    if (s == null) {
      return s;
    }
    String s1 = s;
    try
    {
      switch (i)
      {
      case 2: 
        s1 = new String(s.getBytes("ISO8859-1"), "gb2312");
        break;
      case 0: 
        s1 = new String(s.getBytes("GBK"), "ISO8859-1");
        break;
      case 1: 
        s1 = new String(s.getBytes("ISO8859-1"), "GBK");
        break;
      default: 
        s1 = s;
      }
    }
    catch (UnsupportedEncodingException e)
    {
      return s1;
    }
    return s1;
  }
}
