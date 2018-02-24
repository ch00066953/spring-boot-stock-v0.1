package analyse.subject;

import report.EReport;
import tool.StringX;

/**
 * 科目
 * @author lgwang
 *
 */
public class Subject implements ISubject {
	EReport er;
	int iRow = 0;
	public Subject(EReport er,String sRow) {
		setObject(er);
		setRow(sRow);
	}
	public boolean isAdvance3y() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isKeep3y() {
		// TODO Auto-generated method stub
		return false;
	}

	public double getMean3y() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getExpect() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getMeanDif3y() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getMeanRiseRate3y() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setObject(EReport er){
		this.er = er;
	}
	
	public void setRow(String sRow){
		iRow = this.er.getRow(sRow);
	}

	public boolean isEnough(double i) {
		
		double value = getCell(1);
		if (value > i){
			System.out.println("value："+value);
			return true;
		}
		return false;
	}
	
	public double getCell(int iCol) {
		String cell = er.getCell(iRow, 1);
		double value;
		if (StringX.isEmpty(cell)){
			value = 0;
		}else
			value = Double.valueOf(er.getCell(iRow, 1));
		return value;
	}

	public int getFristYR(){
		return getYearReport(1);
		
		
	}
	
	public int getYearReport(int i){
		String conEnd = "-12-31";
		return i;
	}
}
