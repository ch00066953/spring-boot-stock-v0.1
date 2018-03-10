package tool.fileAnalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tool.StringX;
import tool.fileAnalysis.bean.TableBean;

public class XlsxReader extends TableBean{
	public XSSFWorkbook book;
	private int sheetPage = 0;
	public XSSFSheet sheet;

	public XlsxReader() {
		// TODO Auto-generated constructor stub
	}
	public XlsxReader(String sPath) {
		 try {
			getReport(sPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getReport(String sPath) throws IOException {
		File file = new File(sPath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			book = new XSSFWorkbook(fis);
			setSheet(sheetPage);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fis.close();
		}
		
	}

	private void initCell() {
		for (; iCol < sheet.getRow(sheetPage).getLastCellNum(); ) {
			String sCol = getColName(iCol);
			if(StringX.isEmpty(sCol)){
				break;
			}
			addHead(getColName(iCol));
		}

		for (int r = 0 ; r <= sheet.getLastRowNum(); r++) {
			String sRow = getRowName(iRow);
			if(StringX.isEmpty(sRow)){
				break;
			}
			for(int i = 0; i < iCol; i++){
				
				add(getSheetCell(r, i));
			}
		}
	}
	
	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(int sheet) {
		this.sheet = book.getSheetAt(sheet);
		initCell();
	}
	
	public String getRowName(int iRow) {
		
		return getCellValue(iRow, 0);
	}

	public String getColName(int iCol) {
		return getCellValue(0, iCol);
	}
	
	public String getSheetCell(int iRow,int iCol) {
		return getCellValue(iRow, iCol);
	}
	
	
	public int getSheetRow(String sRow) {
		return rowno.get(sRow);
	}

	public int getCol(String sCol) {
		return colno.get(sCol);
	}
	
	private String getCellValue(int rowIndex, int columnIndex) {
		XSSFCell cell;
		String value = "";
		XSSFRow row = sheet.getRow(rowIndex);
		cell = row.getCell(columnIndex);
		if (cell != null) {
			// 注意：一定要设成这个，否则可能会出现乱码
			switch (cell.getCellTypeEnum()) {
			case STRING:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					if (date != null) {
						value = new SimpleDateFormat("yyyy-MM-dd")
						.format(date);
					} else {
						value = "";
					}
				} else {
					value = String.valueOf(cell.getNumericCellValue());
				}
				break;

			case FORMULA:
				// 导入时如果为公式生成的数据则无值
				if (!cell.getStringCellValue().equals("")) {
					value = cell.getStringCellValue();
				} else {
					value = cell.getNumericCellValue() + "";
				}
				break;

			case BLANK:
				break;

			case ERROR:
				value = "";
				break;

			case BOOLEAN:
				value = (cell.getBooleanCellValue() == true ? "Y" : "N");
				break;

			default:
				value = "";
			}
		}
		return value;
	}
	
	public static void main(String[] args) {
		String sPath = "F:\\002450.xls";
		XlsxReader er = new XlsxReader(sPath);
		String a = er.getCell("净利润同比增长率", "2013-09-30");
		System.out.println(a);
		System.out.println(er.getCell(1,2));
		System.out.println(er.getRowName(1));
		System.out.println(er.getColName(1));
//		er.close();
	}
}
