package wlgtext.jxl;

// 读取Excel的类 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	public static void main(String args[]) {
		try {
			File file = new File("F:\\000001.xls");
			Workbook book = Workbook.getWorkbook(file);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 得到第一列第一行的单元格
			Cell cell1 = sheet.getCell(0, 0);
			String result = cell1.getContents();
			System.out.println(result);
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String[][] getData(File file, int ignoreRows)
			throws FileNotFoundException, IOException, BiffException {

		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;

		// 打开HSSFWorkbook
		Workbook wb = Workbook.getWorkbook(file);
		Cell cell = null;

		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			Sheet st = wb.getSheet(sheetIndex);
			// 第一行为标题，不取
			for (int rowIndex = ignoreRows; rowIndex <= st.getRows() - 1; rowIndex++) {
				Cell[] row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.length + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}

				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.length - 1; columnIndex++) {
					String value = "";
					cell = row[columnIndex];
					if (cell != null) {
						value = cell.getContents();
//						System.out.println(value + "|" +rowIndex +"|" +columnIndex);
					}

					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}
				if (hasValue) {
					result.add(values);

				}
			}
		}

		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 处理后的字符串
	 */

	public static String rightTrim(String str) {

		if (str == null) {

			return "";

		}

		int length = str.length();

		for (int i = length - 1; i >= 0; i--) {

			if (str.charAt(i) != 0x20) {

				break;

			}

			length--;

		}

		return str.substring(0, length);

	}

}