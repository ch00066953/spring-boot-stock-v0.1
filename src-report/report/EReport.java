package report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import tool.StringX;
import tool.fileAnalysis.bean.TableBean;

public class EReport extends TableBean{
	public Workbook book;
	private int sheetPage = 0;
	public Sheet sheet;
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

	private void initCell() {
		for (; iCol < sheet.getColumns(); ) {
			String sCol = getColName(iCol);
			if(StringX.isEmpty(sCol)){
				break;
			}
			addHead(getColName(iCol));
			if(iFristY == 0 && getColName(iCol).contains("-12-31")){
				iFristY = iCol;
				sFristY = getColName(iCol);
			}
		}

		for (int r = 0 ; r < sheet.getRows(); r++) {
			String sRow = getRowName(iRow);
			if(StringX.isEmpty(sRow)){
				break;
			}
			for(int i = 0; i < iCol; i++){
				
				add(getSheetCell(r, i));
				System.out.println(r+","+i);
			}
		}
	}
	
	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(int sheet) {
		this.sheet = book.getSheet(sheet);
		initCell();
	}
	
	public void close() {
		if(book != null)
		book.close();
	}
	
	public String getRowName(int iRow) {
		
		return sheet.getCell(0, iRow).getContents();
	}

	public String getColName(int iCol) {
		return sheet.getCell(iCol, 0).getContents();
	}
	
	public String getSheetCell(int iRow,int iCol) {
		return sheet.getCell(iCol, iRow).getContents();
	}
	
	
	public int getSheetRow(String sRow) {
		return rowno.get(sRow);
	}

	public int getCol(String sCol) {
		return colno.get(sCol);
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
		if (yearNo > colno.size())
			return 0;
		return yearNo;
	}
	
	public static void main(String[] args) {
		String sPath = "F:\\002450.xls";
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
