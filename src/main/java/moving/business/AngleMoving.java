package moving.business;

import moving.*;

public class AngleMoving {

	/**
	 * 综合面
	 */
	public void SYNMoving() {
		System.out.println("综合面迁徙开始...");
		RatingMoving rm = new RatingMoving(new SYN());
		rm.excuse();

	}
	
	/**
	 * 技术面
	 */
	public void TechMoving() {
		System.out.println("技术面迁徙开始...");
		RatingMoving rm = new RatingMoving(new Tech());
		rm.excuse();
		// TODO Auto-generated method stub

	}
	
	/**
	 * 行业面
	 */
	public void TradeMoving() {
		System.out.println("行业面迁徙开始...");
		RatingMoving rm = new RatingMoving(new Trade());
		rm.excuse();

	}
	
	/**
	 * 基本面
	 */
	public void BaseMoving() {
		System.out.println("基本面迁徙开始...");
		RatingMoving rm = new RatingMoving(new Base());
		rm.excuse();

	}
	
	/**
	 * 资金面
	 */
	public void FinanMoving() {
		System.out.println("资金面迁徙开始...");
		RatingMoving rm = new RatingMoving(new Finan());
		rm.excuse();

	}
	
	/**
	 * 信息面
	 */
	public void InfoMoving() {
		System.out.println("信息面迁徙开始...");
		RatingMoving rm = new RatingMoving(new Info());
		rm.excuse();

	}
	
	public void excuse() {
		SYNMoving();
		TechMoving();
		FinanMoving();
		InfoMoving();
		TradeMoving();
		BaseMoving();
	}
	
	public static void main(String[] args) {
		AngleMoving am = new AngleMoving();
		am.excuse();
	}
}
