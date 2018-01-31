package tool.fileAnalysis;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.beanutils.BeanUtils;
 
/**
 * �ļ�����
 */
public class CSVUtils {
 
  /**
   * ����ΪCVS�ļ� 
   * @param exportData
   *       Դ����List
   * @param map
   *       csv�ļ����б�ͷmap
   * @param outPutPath
   *       �ļ�·��
   * @param fileName
   *       �ļ�����
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath,
                   String fileName) {
    File csvFile = null;
    BufferedWriter csvFileOutputStream = null;
    try {
      File file = new File(outPutPath);
      if (!file.exists()) {
        file.mkdir();
      }
      //�����ļ�����ʽ������
      csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
      System.out.println("csvFile��" + csvFile);
      // UTF-8ʹ��ȷ��ȡ�ָ���"," 
      csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
        csvFile), "UTF-8"), 1024);
      System.out.println("csvFileOutputStream��" + csvFileOutputStream);
      // д���ļ�ͷ�� 
      for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
        java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
        csvFileOutputStream
          .write("" + (String) propertyEntry.getValue() != null ? (String) propertyEntry
            .getValue() : "" + "");
        if (propertyIterator.hasNext()) {
          csvFileOutputStream.write(",");
        }
      }
      csvFileOutputStream.newLine();
      // д���ļ����� 
      for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
        Object row = (Object) iterator.next();
        for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
          .hasNext();) {
          java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
            .next();
          csvFileOutputStream.write((String) BeanUtils.getProperty(row,(String) propertyEntry.getKey()));
          if (propertyIterator.hasNext()) {
            csvFileOutputStream.write(",");
          }
        }
        if (iterator.hasNext()) {
          csvFileOutputStream.newLine();
        }
      }
      csvFileOutputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        csvFileOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return csvFile;
  }
 
  /**
   * �����ļ�
   * @param response
   * @param csvFilePath
   *       �ļ�·��
   * @param fileName
   *       �ļ�����
   * @throws IOException
   */
  public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName)
                                                  throws IOException {
    response.setContentType("application/csv;charset=UTF-8");
    response.setHeader("Content-Disposition",
      "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
 
    InputStream in = null;
    try {
      in = new FileInputStream(csvFilePath);
      int len = 0;
      byte[] buffer = new byte[1024];
      response.setCharacterEncoding("UTF-8");
      OutputStream out = response.getOutputStream();
      while ((len = in.read(buffer)) > 0) {
        out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
        out.write(buffer, 0, len);
      }
    } catch (FileNotFoundException e) {
      System.out.println(e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
 
  /**
   * ɾ����Ŀ¼filePath�µ������ļ�
   * @param filePath
   *      �ļ�Ŀ¼·��
   */
  public static void deleteFiles(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
      File[] files = file.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
          files[i].delete();
        }
      }
    }
  }
 
  /**
   * ɾ�������ļ�
   * @param filePath
   *     �ļ�Ŀ¼·��
   * @param fileName
   *     �ļ�����
   */
  public static void deleteFile(String filePath, String fileName) {
    File file = new File(filePath);
    if (file.exists()) {
      File[] files = file.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
          if (files[i].getName().equals(fileName)) {
            files[i].delete();
            return;
          }
        }
      }
    }
  }
 
  /**
   * ��������
   * @param args
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static void main(String[] args) {
    List exportData = new ArrayList<Map>();
    Map row1 = new LinkedHashMap<String, String>();
    row1.put("��һ��", "11");
   /* row1.put("2", "12");
    row1.put("3", "13");
    row1.put("4", "14");*/
    exportData.add(row1);
    row1 = new LinkedHashMap<String, String>();
    row1.put("��һ��", "21");
   /* row1.put("2", "22");
    row1.put("3", "23");
    row1.put("4", "24");*/
    exportData.add(row1);
    LinkedHashMap map = new LinkedHashMap();
    map.put("��һ��", "��һ��");
   /* map.put("�ڶ���", "�ڶ���");
    map.put("������", "������");
    map.put("������", "������");*/
 
    String path = "D:/export/";
    String fileName = "�ļ�����";
    File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
    String fileName2 = file.getName();
    System.out.println("�ļ����ƣ�" + fileName2);
  }
}