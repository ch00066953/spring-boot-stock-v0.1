package wlgtext;

public class Equal {

	public static void main(String[] args) {
		String a = "万科A(000002)个股分析_牛叉诊股_同花顺金融网";
		if (a.indexOf("000002") != -1)
			System.out.println("万科A");
	}
}
