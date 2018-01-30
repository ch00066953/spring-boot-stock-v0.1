package tool.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 现金流折现算法
 * @author lgwang
 *
 */
public class DCF {

	// 自由现金流
	List<Double> fcf = new ArrayList<Double>();
	// 折现后的自由现金流
	List<Double> dfcf = new ArrayList<Double>();
	// 现金增值率
	List<Double> cvad = new ArrayList<Double>();
	// 折现率
	double dr = 0.09;
	// 永续年金率
	double par = 0.05;
	// 永续年金
	double pa;
	// 总股本
	double gc;
	// 价格
	double value = 0 ;
	// 安全价格
	double sv = 0 ;

	private void isCalced() {
//		if(value != 0 && this.fcf.get(0).equals(fcf)&& this.)

	}
	/**
	 * 
	 * @param fcf 初期现金流
	 * @param cvad 固定增值率
	 * @param gc 总股本
	 */
	public void init(double fcf,double cvad,double gc){
		setCvad(cvad);
		setFcfbyCvad(fcf);
		setGc(gc);
		
		setDfcf();
		setPa();
	}
	
	public double simpleCalc() {
		double sum = 0 ;
		double total = 0;
		
		for(int i=1;i<=10;i++){
			sum += dfcf.get(i);
		}
		total = sum + pa;
		value = total / gc;
		sv = value * 0.8;
		return value;

	}
	public List<Double> getFcf() {
		return fcf;
	}

	public void setFcf(List<Double> fcf) {
		this.fcf = fcf;
	}
	
	/**
	 * 根据初期现金与增长率算出自由现金流
	 * @param d
	 */
	public void setFcfbyCvad(double d) {
		this.fcf.add(d);
		for (int i = 1; i <= 11; i++)
			this.fcf.add(fcf.get(i-1)*(1+cvad.get(i-1)));
	}

	public List<Double> getDfcf() {
		return dfcf;
	}

	public void setDfcf(List<Double> dfcf) {
		this.dfcf = dfcf;
	}
	
	public void setDfcf() {
		int i = 0;
		for(Double fcf : this.fcf){
			dfcf.add(fcf/Math.pow((1+dr),i));
			i++;
		}
	}

	public List<Double> getCvad() {
		return cvad;
	}

	/**
	 * 固定增值率
	 * @param d
	 */
	public void setCvad(double d) {
		for (int i = 0; i <= 11; i++)
			this.cvad.add(d);
	}
	/**
	 * 等比递减增值率
	 * @param d
	 * @param r
	 */
	public void setCvad(double d,double r) {
		this.cvad.add(d);
		for (int i = 1; i <= 11; i++)
			this.cvad.add(cvad.get(i-1)*(1-r));
	}

	public void setCvad(List<Double> cvad) {
		this.cvad = cvad;
	}

	public double getDr() {
		return dr;
	}

	public void setDr(double dr) {
		this.dr = dr;
	}

	public double getPar() {
		return par;
	}

	public void setPar(double par) {
		this.par = par;
	}

	public double getGc() {
		return gc;
	}

	public void setGc(double gc) {
		this.gc = gc;
	}

	public void setPa(double pa) {
		this.pa = pa;
	}
	
	public void setPa() {
		this.pa = dfcf.get(11)/(dr-par);
	}

	public double getSv() {
		return sv;
	}
	public void setSv(double sv) {
		this.sv = sv;
	}
	public static void main(String[] args) {
		DCF dcf = new DCF();
		dcf.init(65710.57, 0.13, 65402.15);
		System.out.println(dcf.getFcf());
		System.out.println(dcf.getDfcf());
		System.out.println(dcf.simpleCalc());
		System.out.println(dcf.getSv());
		
	}
	
}
