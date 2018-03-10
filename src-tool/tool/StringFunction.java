package tool;

import java.io.PrintStream;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

public class StringFunction
{
  private static int[] weight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
  private static char[] vcode = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
  private static String hzDigital = "([１一壹])|([２二贰])|([３三叁])|([４四肆])|([５五伍])|([６六陆])|([７七柒])|([８八捌])|([９九玖])|([０〇零])";
  private static Pattern dpattern = Pattern.compile(hzDigital);
  
  public static String removeSpaces(String s)
  {
    StringTokenizer st = new StringTokenizer(s, " ", false);
    String t = "";
    while (st.hasMoreElements()) {
      t = t + st.nextElement();
    }
    return t;
  }
  
  /**
   * @deprecated
   */
  public static String ltrim(String source)
  {
    return StringX.trimStart(source);
  }
  
  /**
   * @deprecated
   */
  public static String rtrim(String s)
  {
    return StringX.trimEnd(s);
  }
  
  /**
   * @deprecated
   */
  public static String itrim(String s)
  {
    return s.replaceAll("\\b\\s{2,}\\b", " ");
  }
  
  /**
   * @deprecated
   */
  public static String trim(String s)
  {
    return itrim(ltrim(rtrim(s)));
  }
  
  /**
   * @deprecated
   */
  public static String lrtrim(String s)
  {
    return ltrim(rtrim(s));
  }
  
  public static String getFileName(String filePathName)
  {
    filePathName = DataConvert.toString(filePathName);
    int pos = 0;
    pos = filePathName.lastIndexOf('/');
    if (pos != -1) {
      return filePathName.substring(pos + 1, filePathName.length());
    }
    pos = filePathName.lastIndexOf('\\');
    if (pos != -1) {
      return filePathName.substring(pos + 1, filePathName.length());
    }
    return filePathName;
  }
  
  public static String replace(String s, String sOld, String sNew)
  {
    if ((s != null) && (s.length() < 256))
    {
      int iPos = s.indexOf(sOld, 0);
      while (iPos != -1)
      {
        s = s.substring(0, iPos) + sNew + s.substring(iPos + sOld.length());
        iPos = s.indexOf(sOld, iPos + sNew.length());
      }
      return s;
    }
    if (s == null) {
      return null;
    }
    Pattern p = null;
    Matcher mm = null;
    
    p = Pattern.compile(convert2Reg(sOld));
    mm = p.matcher(s);
    return mm.replaceAll(sNew);
  }
  
  public static String convert2Reg(String src)
  {
    Hashtable hs = new Hashtable();
    hs.put(new Character('\n'), new Character('n'));
    hs.put(new Character('\r'), new Character('r'));
    hs.put(new Character('\\'), new Character('\\'));
    hs.put(new Character('{'), new Character('{'));
    hs.put(new Character('['), new Character('['));
    hs.put(new Character('$'), new Character('$'));
    hs.put(new Character('^'), new Character('^'));
    hs.put(new Character('|'), new Character('|'));
    hs.put(new Character('('), new Character('('));
    hs.put(new Character(')'), new Character(')'));
    hs.put(new Character('*'), new Character('*'));
    hs.put(new Character('+'), new Character('+'));
    StringBuffer sb = new StringBuffer();
    char[] arr = src.toCharArray();
    for (int i = 0; i < arr.length; i++)
    {
      Character ch = new Character(arr[i]);
      if (hs.containsKey(ch))
      {
        sb.append('\\');
        sb.append(((Character)hs.get(ch)).charValue());
      }
      else
      {
        sb.append(arr[i]);
      }
    }
    return sb.toString();
  }
  
  public static String applyArgs(String sCode, String sArgs, String sArgsValue)
  {
    StringTokenizer stArgs = new StringTokenizer(sArgs.trim(), ", ");
    StringTokenizer stArgsValue = new StringTokenizer(sArgsValue.trim(), ",");
    while ((stArgs.hasMoreTokens()) && (stArgsValue.hasMoreTokens()))
    {
      String sArgType = stArgs.nextToken().trim();
      String sArgName = stArgs.nextToken().trim();
      String sArgValue = stArgsValue.nextToken().trim();
      if (!sArgType.equals("String")) {
        if (sArgType.equals("Number"))
        {
          if ((sArgValue.substring(0, 1).equals("'")) || (sArgValue.substring(0, 1).equals(String.valueOf('"')))) {
            sArgValue = sArgValue.substring(1, sArgValue.length() - 1);
          }
        }
        else if (!sArgType.equals("Date")) {}
      }
      sCode = replace(sCode, "#" + sArgName, sArgValue);
    }
    return sCode;
  }
  
  public static String getNow()
  {
    Calendar rightNow = Calendar.getInstance();
    return String.valueOf(Time.valueOf(String.valueOf(rightNow.get(11)) + ":" + String.valueOf(rightNow.get(12)) + ":" + String.valueOf(rightNow.get(13))));
  }
  
  public static int indexOf(String sSource, String sTarget, String sDelim1, String sDelim2, int iBeginPos)
  {
    int iPos = sSource.indexOf(sTarget, iBeginPos);
    while ((iPos >= 0) && (getOccurTimes(sSource, sDelim1, sDelim2, iBeginPos, iPos) != 0)) {
      iPos = sSource.indexOf(sTarget, iPos + sTarget.length());
    }
    return iPos;
  }
  
  public static int indexOf(String sSource, String sTarget, int iBeginPos, int iEndPos)
  {
    return sSource.substring(iBeginPos, iEndPos).indexOf(sTarget);
  }
  
  public static int getOccurTimes(String sSource, String sDelim1, String sDelim2, int iBeginPos, int iEndPos)
  {
    sSource = sSource.substring(iBeginPos, iEndPos);
    if (sDelim1.equals(sDelim2)) {
      return getOccurTimes(sSource, sDelim1) % 2;
    }
    return getOccurTimes(sSource, sDelim1) - getOccurTimes(sSource, sDelim2);
  }
  
  public static int getOccurTimes(String sSource, String sDelim)
  {
    int iPos = 0;int iCount = 0;
    int iDelLen = sDelim.length();
    while ((iPos = sSource.indexOf(sDelim, iPos) + iDelLen) > iDelLen - 1) {
      iCount++;
    }
    return iCount;
  }
  
  public static int getOccurTimesIgnoreCase(String sSource, String sDelim)
  {
    int iPos = 0;int iCount = 0;
    int iDelLen = sDelim.length();
    while ((iPos = sSource.toLowerCase().indexOf(sDelim.toLowerCase(), iPos) + iDelLen) > iDelLen - 1) {
      iCount++;
    }
    return iCount;
  }
  
  public static String getSeparate(String sSource, String sDelim, int iOrder)
  {
    int iLastPos = 0;int iCount = 0;
    int iDelLen = sDelim.length();
    sSource = sSource + sDelim;
    int iPos;
    while ((iPos = sSource.indexOf(sDelim, iLastPos)) >= 0)
    {
      iCount++;
      if (iCount == iOrder) {
        return sSource.substring(iLastPos, iPos);
      }
      iLastPos = iPos + iDelLen;
    }
    return "";
  }
  
  public static int getSeparateSum(String sSource, String sDelim)
  {
    if (sSource == null) {
      return 0;
    }
    int iPos = 0;int iCount = 0;
    int iDelLen = sDelim.length();
    while ((iPos = sSource.indexOf(sDelim, iPos) + iDelLen) > iDelLen - 1) {
      iCount++;
    }
    return iCount + 1;
  }
  
  public static String[] toStringArray(String sSource, String sDelim)
  {
    String[] sList = null;
    int iCount = getSeparateSum(sSource, sDelim);
    if (iCount > 0)
    {
      sList = new String[iCount];
      for (int i = 1; i <= iCount; i++) {
        sList[(i - 1)] = getSeparate(sSource, sDelim, i);
      }
    }
    return sList;
  }
  
  public static String toArrayString(String[] sSource, String sDelim)
  {
    String sList = "";
    int iCount = sSource.length;
    for (int i = 1; i <= iCount; i++) {
      sList = sList + sDelim + sSource[(i - 1)];
    }
    if (sList.length() > 0) {
      sList = sList.substring(sDelim.length());
    }
    return sList;
  }
  
  public static String[] doubleArray(String[] sSource)
  {
    String[] sNewArray = null;
    
    int iCount = sSource.length;
    sNewArray = new String[2 * iCount];
    if (iCount > 0) {
      for (int i = 0; i < iCount; i++)
      {
        sNewArray[(2 * i)] = sSource[i];
        sNewArray[(2 * i + 1)] = sSource[i];
      }
    }
    return sNewArray;
  }

  public static String date2String(java.sql.Date dateDate, String sFormat)
  {
    String sToday = dateDate.toString();
    sToday = replace(sToday, "-", sFormat);
    return sToday;
  }
  
  public static String[] encodingStrings(String[] data, String code)
  {
    int length = data.length;
    String[] result = new String[length];
    try
    {
      for (int i = 0; i < length; i++) {
        result[i] = new String(data[i].getBytes(code));
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return result;
  }
  
  public static String getProfileString(String data, String key, String delim)
  {
    if (data == null) {
      return "";
    }
    int curIndex = data.indexOf(key + "=");
    if (curIndex < 0) {
      return "";
    }
    int endIndex = (data + delim).indexOf(delim, curIndex);
    if (endIndex < 0) {
      return "";
    }
    int beginIndex = data.indexOf("=", curIndex);
    if (beginIndex < 0) {
      return "";
    }
    return data.substring(beginIndex + 1, endIndex);
  }
  
  public static String getProfileString(String data, String key)
  {
    return getProfileString(data, key, ";");
  }
  
  public static String[] toStringArray(String data, String key1, String key2, int order)
  {
    if ((key1 == null) || (key1.equals(""))) {
      key1 = " ";
    }
    if ((key2 == null) || (key2.equals(""))) {
      key2 = " ";
    }
    int iCount = getSeparateSum(data, key1);
    String[] sArray = new String[iCount];
    for (int i = 1; i <= iCount; i++)
    {
      String sTemp = getSeparate(data, key1, i);
      sArray[(i - 1)] = getSeparate(sTemp, key2, order);
    }
    return sArray;
  }
  
  public static boolean isLike(String source, String destination)
  {
    int iSourceLength = source.length();
    int iDesLength = destination.length();
    if (replace(destination, "%", "").length() > iSourceLength) {
      return false;
    }
    int j = 0;
    int i = 0;
    boolean isLike = true;
    char chSource = ' ';
    char chDes = ' ';
    while (((i < iSourceLength) || (j < iDesLength)) && (isLike))
    {
      if (i < iSourceLength) {
        chSource = source.charAt(i);
      }
      if (j < iDesLength) {
        chDes = destination.charAt(j);
      }
      if (chSource == chDes)
      {
        i++;
        j++;
      }
      else if (chDes == '_')
      {
        if ((j < iDesLength) && (i < iSourceLength)) {
          return isLike(source.substring(i + 1), destination.substring(j + 1));
        }
        isLike = false;
      }
      else if (chDes == '%')
      {
        int underLines = 0;
        j++;
        while (((chDes == '_') || (chDes == '%')) && (j < iDesLength))
        {
          if (chDes == '_') {
            underLines++;
          }
          chDes = destination.charAt(j);
          j++;
        }
        j--;
        if (chDes != '%')
        {
          boolean subLike = false;
          i = source.indexOf(chDes, i + underLines);
          while ((i >= 0) && (!subLike))
          {
            subLike = isLike(source.substring(i), destination.substring(j));
            i = source.indexOf(chDes, i + 1);
          }
          return subLike;
        }
        i++;
        j = iDesLength;
      }
      else
      {
        isLike = false;
      }
    }
    return isLike;
  }
  
  public static boolean setAttribute(String[][] sAttributes, String sAttributeName, String sAttributeValue)
  {
    boolean bSuccess = false;
    for (int i = 0; i < sAttributes.length; i++) {
      if (sAttributes[i][0].equalsIgnoreCase(sAttributeName))
      {
        sAttributes[i][1] = sAttributeValue;bSuccess = true;
        return bSuccess;
      }
    }
    return bSuccess;
  }
  
  public static String getAttribute(String[][] sAttributes, String sAttributeName)
  {
    return getAttribute(sAttributes, sAttributeName, 0, 1);
  }
  
  public static String getAttribute(String[][] sAttributes, String sAttributeName, int iAttID, int iAttValue)
  {
    String sAttributeValue = null;
    for (int i = 0; i < sAttributes.length; i++) {
      if (sAttributes[i][iAttID].equalsIgnoreCase(sAttributeName))
      {
        sAttributeValue = sAttributes[i][iAttValue];
        return sAttributeValue;
      }
    }
    return sAttributeValue;
  }
  
  public static long getQuot(String sDate1, String sDate2)
  {
    long quot = 0L;
    SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
    try
    {
      java.util.Date date1 = ft.parse(sDate1);
      java.util.Date date2 = ft.parse(sDate2);
      quot = date1.getTime() - date2.getTime();
      quot = quot / 1000L / 60L / 60L / 24L;
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return quot;
  }
  
  public static String getRelativeAccountMonth(String sAccountMonth, String sType, int iStep)
  {
    String sYear = sAccountMonth.substring(0, 4);
    String sMonth = sAccountMonth.substring(5, 7);
    
    int iYear = Integer.valueOf(sYear).intValue();
    int iMonth = Integer.valueOf(sMonth).intValue();
    if (sType.equalsIgnoreCase("year"))
    {
      iYear += iStep;
    }
    else if (sType.equalsIgnoreCase("month"))
    {
      iYear += (iMonth + iStep) / 12;
      iMonth = (iMonth + iStep) % 12;
      if (iMonth <= 0)
      {
        iYear--;
        iMonth += 12;
      }
    }
    sYear = String.valueOf(iYear);
    sMonth = String.valueOf(iMonth);
    if (sMonth.length() == 1) {
      sMonth = "0" + sMonth;
    }
    return sYear + "/" + sMonth;
  }
  
  public static String numberToChinese(double doubleNum)
  {
    DecimalFormat df = new DecimalFormat("############0.00");
    String sNum = df.format(doubleNum);
    

    int pointPos = sNum.indexOf(".");
    if (sNum.substring(pointPos).compareTo(".00") == 0) {
      sNum = sNum.substring(0, pointPos);
    }
    String temp = "";
    String[] sBIT = new String[4];
    String[] sUNIT = new String[4];
    String[] sCents = new String[2];
    String sIntD = "";
    String sDecD = "";
    String NtoC = "";
    int iCount = 0;
    int lStartPos = 0;
    int iLength = 0;
    
    sBIT[0] = "";
    sBIT[1] = "拾";
    sBIT[2] = "佰";
    sBIT[3] = "仟";
    sUNIT[0] = "";
    sUNIT[1] = "万";
    sUNIT[2] = "亿";
    sUNIT[3] = "万";
    sCents[0] = "分";
    sCents[1] = "角";
    if ((sNum.compareTo("0") == 0) || (sNum.compareTo("0.0") == 0) || (sNum.compareTo("0.00") == 0))
    {
      NtoC = "零元整";
      return NtoC;
    }
    if (sNum.indexOf(".") > 0) {
      temp = sNum.substring(0, sNum.indexOf("."));
    } else {
      temp = sNum;
    }
    iCount = temp.length() % 4 != 0 ? temp.length() / 4 + 1 : temp.length() / 4;
    for (int i = iCount; i >= 1; i--)
    {
      if ((i == iCount) && (temp.length() % 4 != 0)) {
        iLength = temp.length() % 4;
      } else {
        iLength = 4;
      }
      sIntD = temp.substring(lStartPos, lStartPos + iLength);
      for (int j = 0; j < sIntD.length(); j++) {
        if (Integer.parseInt(sIntD.substring(j, j + 1)) != 0) {
          switch (Integer.parseInt(sIntD.substring(j, j + 1)))
          {
          case 1: 
            NtoC = NtoC + "壹" + sBIT[(sIntD.length() - j - 1)];
            break;
          case 2: 
            NtoC = NtoC + "贰" + sBIT[(sIntD.length() - j - 1)];
            break;
          case 3: 
            NtoC = NtoC + "叁" + sBIT[(sIntD.length() - j - 1)];
            break;
          case 4: 
            NtoC = NtoC + "肆" + sBIT[(sIntD.length() - j - 1)];
            break;
          case 5: 
            NtoC = NtoC + "伍" + sBIT[(sIntD.length() - j - 1)];
            break;
          case 6: 
            NtoC = NtoC + "陆" + sBIT[(sIntD.length() - j - 1)];
            break;
          case 7: 
            NtoC = NtoC + "柒" + sBIT[(sIntD.length() - j - 1)];
            break;
          case 8: 
            NtoC = NtoC + "捌" + sBIT[(sIntD.length() - j - 1)];
            break;
          case 9: 
            NtoC = NtoC + "玖" + sBIT[(sIntD.length() - j - 1)];
          }
        } else if ((j + 1 < sIntD.length()) && (sIntD.charAt(j + 1) != '0')) {
          NtoC = NtoC + "零";
        }
      }
      lStartPos += iLength;
      if (i < iCount)
      {
        if ((Integer.parseInt(sIntD.substring(sIntD.length() - 1, sIntD.length())) != 0) || (Integer.parseInt(sIntD.substring(sIntD.length() - 2, sIntD.length() - 1)) != 0) || (Integer.parseInt(sIntD.substring(sIntD.length() - 3, sIntD.length() - 2)) != 0) || (Integer.parseInt(sIntD.substring(sIntD.length() - 4, sIntD.length() - 3)) != 0)) {
          NtoC = NtoC + sUNIT[(i - 1)];
        }
      }
      else {
        NtoC = NtoC + sUNIT[(i - 1)];
      }
    }
    if (NtoC.length() > 0) {
      NtoC = NtoC + "元";
    }
    if (sNum.indexOf(".") > 0)
    {
      sDecD = sNum.substring(sNum.indexOf(".") + 1);
      for (int i = 0; i < 2; i++) {
        if (Integer.parseInt(sDecD.substring(i, i + 1)) != 0) {
          switch (Integer.parseInt(sDecD.substring(i, i + 1)))
          {
          case 1: 
            NtoC = NtoC + "壹" + sCents[(1 - i)];
            break;
          case 2: 
            NtoC = NtoC + "贰" + sCents[(1 - i)];
            break;
          case 3: 
            NtoC = NtoC + "叁" + sCents[(1 - i)];
            break;
          case 4: 
            NtoC = NtoC + "肆" + sCents[(1 - i)];
            break;
          case 5: 
            NtoC = NtoC + "伍" + sCents[(1 - i)];
            break;
          case 6: 
            NtoC = NtoC + "陆" + sCents[(1 - i)];
            break;
          case 7: 
            NtoC = NtoC + "柒" + sCents[(1 - i)];
            break;
          case 8: 
            NtoC = NtoC + "捌" + sCents[(1 - i)];
            break;
          case 9: 
            NtoC = NtoC + "玖" + sCents[(1 - i)];
          }
        } else if (NtoC.length() > 0) {
          NtoC = NtoC + "零";
        }
      }
    }
    else
    {
      NtoC = NtoC + "整";
    }
    if (NtoC.substring(NtoC.length() - 1).compareTo("零") == 0) {
      NtoC = NtoC.substring(0, NtoC.length() - 1);
    }
    return NtoC;
  }
  
  public static String macroReplace(String[][] sAttributes, String sSource, String sBeginIdentifier, String sEndIdentifier)
    throws Exception
  {
    return macroReplace(sAttributes, sSource, sBeginIdentifier, sEndIdentifier, 0, 1);
  }
  
  public static String macroReplace(String[][] sAttributes, String sSource, String sBeginIdentifier, String sEndIdentifier, int iAttID, int iAttValue)
    throws Exception
  {
    int iPosBegin = 0;int iPosEnd = 0;
    String sAttributeID = "";
    String sReturn = sSource;
    try
    {
      if ((sAttributes == null) || (sAttributes.length == 0) || (sSource == null)) {
        return null;
      }
      while ((iPosBegin = sReturn.indexOf(sBeginIdentifier, iPosBegin)) >= 0)
      {
        iPosEnd = sReturn.indexOf(sEndIdentifier, iPosBegin);
        sAttributeID = sReturn.substring(iPosBegin, iPosEnd + sEndIdentifier.length());
        sReturn = sReturn.substring(0, iPosBegin) + getAttribute(sAttributes, sAttributeID, iAttID, iAttValue) + sReturn.substring(iPosEnd + sEndIdentifier.length());
      }
    }
    catch (Exception ex)
    {
      throw new Exception("宏替换错误:" + ex.toString());
    }
    return sReturn;
  }
  
  
  public static String macroRepeat(String[] sAttributes, String sSource)
    throws Exception
  {
    String sReturn = sSource;
    for (int i = 0; i < sAttributes.length; i++) {
      sReturn = sReturn + replace(sReturn, "[$CIRCULATOR/$]", sAttributes[i]);
    }
    return sReturn;
  }
  
  public static String getXProfileString(String sSource, String sBeginIdentifier, String sEndIdentifier)
  {
    return sSource.substring(sSource.indexOf(sBeginIdentifier) + sBeginIdentifier.length(), sSource.indexOf(sEndIdentifier));
  }
  
  public static String replaceConstants(String src, String fnd, String rep)
    throws Exception
  {
    if ((src == null) || (src.equals(""))) {
      return "";
    }
    String dst = src;
    
    int idx = dst.indexOf(fnd);
    while (idx >= 0)
    {
      dst = dst.substring(0, idx) + rep + dst.substring(idx + fnd.length(), dst.length());
      idx = dst.indexOf(fnd, idx + rep.length());
    }
    return dst;
  }
  
  
  public static String getMathRandom()
  {
    String r1 = Double.toString(Math.random());
    if (r1.length() == 18) {
      return r1;
    }
    if (r1.length() > 18) {
      return r1.substring(0, 18);
    }
    if (r1.length() < 18) {
      return (r1 + "00000000").substring(0, 18);
    }
    return r1;
  }
  
  public static String[][] genStringArray(String s)
  {
    int beginDelim = getSeparateSum(s, "{") - 1;
    int endDelim = getSeparateSum(s, "}") - 1;
    int totalColDelim = getSeparateSum(s, "\"") - 1;
    int totalElement = totalColDelim / 2;
    int rowCount = beginDelim - 1;
    int colCount = 0;
    int rowPoint = 0;
    int colPoint = 0;
    colCount = totalElement / rowCount;
    String sTemp = "";
    if ((beginDelim < 2) || (beginDelim != endDelim) || (totalColDelim % 2 != 0)) {
      return (String[][])null;
    }
    String sCur = s.trim();
    sCur = sCur.substring(sCur.indexOf("{") + 1, sCur.lastIndexOf("}")).trim();
    
    String[][] strArray2 = new String[rowCount][colCount];
    for (int irow = 0; irow < rowCount; irow++)
    {
      beginDelim = sCur.indexOf("{", rowPoint) + 1;
      endDelim = sCur.indexOf("}", rowPoint);
      rowPoint = endDelim + 1;
      if (beginDelim > endDelim) {
        return (String[][])null;
      }
      sTemp = sCur.substring(beginDelim, endDelim).trim();
      System.out.println(sTemp);
      for (int icol = 0; icol < colCount; icol++)
      {
        beginDelim = sTemp.indexOf("\"", colPoint) + 1;
        endDelim = sTemp.indexOf("\"", beginDelim);
        strArray2[irow][icol] = sTemp.substring(beginDelim, endDelim);
        System.out.println("strArray2[" + irow + "][" + icol + "]=" + strArray2[irow][icol]);
        colPoint = endDelim + 1;
      }
      colPoint = 0;
      if (rowPoint >= sCur.length()) {
        break;
      }
    }
    return strArray2;
  }
  
  public static String filterControlChar(String str)
  {
    return str.replaceAll("\\p{Cntrl}", "");
  }
  
  public static String fixDigital(String str)
  {
    Matcher m = dpattern.matcher(str);
    StringBuffer sb = new StringBuffer();
    while (m.find())
    {
      int g = 0;
      for (int i = 1; i <= 10; i++) {
        if (m.group(i) != null)
        {
          g = i;
          break;
        }
      }
      m.appendReplacement(sb, String.valueOf(g % 10));
    }
    m.appendTail(sb);
    return sb.toString();
  }
  
  public static String fixPID(String str)
  {
    String s = filterControlChar(str);
    
    String ID15 = fixDigital(s);
    if (ID15.length() != 15) {
      return ID15.toUpperCase();
    }
    StringBuffer ID18 = new StringBuffer(ID15);
    ID18.insert(6, "19");
    int vsum = 0;
    for (int i = 0; i < 17; i++) {
      vsum += Character.digit(ID18.charAt(i), 10) * weight[i];
    }
    ID18.append(vcode[(vsum % 11)]);
    return ID18.toString();
  }
  
  public static void main(String[] args)
  {
    System.out.println(fixPID("150202760929122"));
  }
}

