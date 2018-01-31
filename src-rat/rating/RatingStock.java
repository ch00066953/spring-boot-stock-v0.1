package rating;

import java.util.ArrayList;
import java.util.List;

public class RatingStock {

	String[] stock = new String[]{"000063","002415","000002"};
	
	public void rating() {
		int i;
		int j;
		CatchRatingData crd = new CatchRatingData();
		try {
			i = stock.length;
			for(j=0;j<i;j++)
			crd.catchRating(stock[j]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String[]> rating(List<String> list) {
		final List<String[]> listArray = new ArrayList<String[]>();
		int i = 0;
		for (String stockNO : list){
			try {
				CatchRatingData crd = new CatchRatingData();
				crd.catchRating(stockNO);
				listArray.add(crd.getsRating());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listArray;
			
	}
	
	public String[] rating(String stockNO) {
		String[] sArray = new String[7];
			try {
				CatchRatingData crd = new CatchRatingData();
				crd.catchRating(stockNO);
				sArray = crd.getsRating();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sArray;
			
	}
	
	public static void main(String[] args) {
		RatingStock rs = new RatingStock();
		rs.rating();
	}
}
