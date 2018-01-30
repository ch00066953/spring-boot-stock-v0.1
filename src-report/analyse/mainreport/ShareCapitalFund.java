package analyse.mainreport;

import report.EReport;
import analyse.subject.Subject;

/**
 * 每股资本公积金
 * @author lgwang
 *
 */
public class ShareCapitalFund extends Subject{

	private static String subType = "每股资本公积金";
	
	public ShareCapitalFund(EReport er) {
		super(er,subType);
		// TODO Auto-generated constructor stub
	}
	public ShareCapitalFund(EReport er, String sRow) {
		super(er, sRow);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String sPath = "F:\\000001.xls";
		EReport er = new EReport(sPath);
		ShareCapitalFund scf = new ShareCapitalFund(er,"每股资本公积金");
		scf.isEnough(3);
		er.close();
	}

}
