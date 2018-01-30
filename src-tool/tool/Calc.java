package tool;

public class Calc {

	/**
	 * 是否连续增长
	 * @param db 连续数值
	 * @return
	 */
	public static boolean isAdvance(double ...db) {
		if(db.length <= 1 )
			return false;
		for (int i = 0; i < db.length -1 ; i++){
			if(db[i] <= db[i+1])
				return false;
		}
		return true;
	}
	
	/**
	 * 浮动范围内保持一定增长
	 * @param f 浮动范围
	 * @param db 数组
	 * @return
	 */
	public static boolean isKeep(double f,double ...db){
		if(f > 1 || f< 0){
			System.out.println("浮动范围出错！！！");
		}
		if(db.length <= 1 )
			return false;
		for (int i = 0; i < db.length -1 ; i++){
			if(db[i] <= db[i+1]*(1 - f))
				return false;
		}
		return true;
	}
	
	/**
	 * 平均数
	 * @param db
	 * @return
	 */
	public static double getMean(double ...db) {
		double value = 0;
		for (double d : db)
			value += d;
		
		value /= db.length;
		
		return value;
	}

	/**
	 * 标准差
	 * @param db
	 * @return
	 */
	public static double getMeanDif(double ...db) {
		double mean = getMean(db);
		double sum = 0;
        for(int i = 0;i < db.length;i++){
            sum += Math.sqrt((db[i] -mean) * (db[i] -mean));
        }
		
        return sum / db.length;
	}
	/**
	 * 平均增长率
	 * @param db
	 * @return
	 */
	public static double  getMeanRiseRate(double ...db) {
		double sum = 0;
		int i = 0;
		if (db.length <= 1)
			return 0;
		for (; i < db.length -1 ; i++)
			sum += (db[i] - db[i+1])/db[i+1];
		return sum/ i;
		
	}
	
	public static double  getExpect(){
		return 0;
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(isAdvance(4,3,2,2));;
		System.out.println(isKeep(0.1,2,2,2,2));;
		System.out.println(getMean(0.1,2,2,2,2));;
		System.out.println(getMeanDif(0.1,2,2,2,2));;
		System.out.println(getMeanRiseRate(0.1,2,2,2,2));;
	}
}
