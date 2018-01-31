package tool.fileAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class EReport {
	public Workbook book;
	private int sheetPage = 0;
	public Sheet sheet;
	public Map<String, Integer> col;
	public Map<String, Integer> row;
	private String sFristY ="";
	private int iFristY = 0;

	public EReport() {
		// TODO Auto-generated constructor stub
	}
	public EReport(String sPath) {
		 try {
			getReport(sPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getReport(String sPath) throws FileNotFoundException,
			BiffException, IOException {
		File file = new File(sPath);
		book = Workbook.getWorkbook(file);
		setSheet(sheetPage);
		
	}
	public void getReportByURL(String url) throws FileNotFoundException,
	BiffException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		book = Workbook.getWorkbook(entity.getContent());
		setSheet(sheetPage);
		
	}

	private void initCell() {
		int iRow = 0;
		int iCol = 0;
		col = new HashMap<String, Integer>();
		row = new HashMap<String, Integer>();
		for (; iCol < sheet.getColumns(); iCol++) {
			col.put(getColName(iCol),iCol);
			if(iFristY == 0 && getColName(iCol).contains("-12-31")){
				iFristY = iCol;
				sFristY = getColName(iCol);
			}
		}

		for (; iRow < sheet.getRows(); iRow++) {
			row.put(getRowName(iRow),iRow);
		}
	}
	
	/**
	 * 根据行列号获取内容
	 * @param iCol 列号
	 * @param iRow 行号
	 * @return
	 */
	public String getCell(int iCol, int iRow) {
		// TODO Auto-generated method stub
		// 得到第一列第一行的单元格
		Cell cell1 = sheet.getCell(iCol, iRow);
		String result = cell1.getContents();
		return result;

	}

	public String getCell(String sRow, String sCol) {
		// TODO Auto-generated method stub
		int iRow = 0;
		int iCol = 0;

//		c :for (; iCol < sheet.getColumns(); iCol++) {
//			if(sheet.getCell(iCol, 0).getContents().equals(sCol))
//				break c;
//		}
//
//		r :for (; iRow < sheet.getRows(); iRow++) {
//			if(sheet.getCell(0, iRow).getContents().equals(sRow))
//				break r;
//		}
		iCol = getCol(sCol);
		iRow = getRow(sRow);
		String result = getCell(iCol,iRow );
		return result;
	}
	

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(int sheet) {
		this.sheet = book.getSheet(sheet);
		initCell();
	}
	
	public void close() {
		book.close();
	}
	
	public String getRowName(int iRow) {
		return sheet.getCell(0, iRow).getContents();
	}

	public String getColName(int iCol) {
		return sheet.getCell(iCol, 0).getContents();
	}
	
	public int getRow(String sRow) {
		return row.get(sRow);
	}

	public int getCol(String sCol) {
		return col.get(sCol);
	}
	
	public String getsFristY() {
		return sFristY;
	}
	public int getiFristY() {
		return iFristY;
	}
	
	public int getYearNo(int i){
		i -= 1;
		int yearNo = iFristY + 4 * i ;
		if (yearNo > col.size())
			return 0;
		return yearNo;
	}
	
	public static void main(String[] args) {
		String sPath = "F:\\000001.xls";
		EReport er = new EReport(sPath);
		String a = er.getCell("净利润同比增长率", "2013-09-30");
		System.out.println(a);
		System.out.println(er.getCell(1,2));
		System.out.println(er.getRowName(1));
		System.out.println(er.getColName(1));
		System.out.println(er.getsFristY());
		System.out.println(er.getiFristY());
		System.out.println(er.getYearNo(2));
		er.close();
	}
}
