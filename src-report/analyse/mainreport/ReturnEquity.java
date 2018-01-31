package analyse.mainreport;

import report.EReport;
import analyse.subject.Subject;

/**
 * 净资产收益率
 * @author lgwang
 *
 */
public class ReturnEquity extends Subject {

	private static String subType = "净资产收益率";
	
	public ReturnEquity(EReport er) {
		super(er,subType);
		// TODO Auto-generated constructor stub
	}
	
	public ReturnEquity(EReport er, String sRow) {
		super(er, sRow);
	}

	public static void main(String[] args) {
		String sPath = "F:\\000001.xls";
		EReport er = new EReport(sPath);
		ReturnEquity scf = new ReturnEquity(er);
		scf.isEnough(25);
		er.close();
	}
}
