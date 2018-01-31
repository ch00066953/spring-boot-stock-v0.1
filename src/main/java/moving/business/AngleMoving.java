package moving.business;

import moving.*;

public class AngleMoving {

	/**
	 * �ۺ���
	 */
	public void SYNMoving() {
		System.out.println("�ۺ���Ǩ�㿪ʼ...");
		RatingMoving rm = new RatingMoving(new SYN());
		rm.excuse();

	}
	
	/**
	 * ������
	 */
	public void TechMoving() {
		System.out.println("������Ǩ�㿪ʼ...");
		RatingMoving rm = new RatingMoving(new Tech());
		rm.excuse();
		// TODO Auto-generated method stub

	}
	
	/**
	 * ��ҵ��
	 */
	public void TradeMoving() {
		System.out.println("��ҵ��Ǩ�㿪ʼ...");
		RatingMoving rm = new RatingMoving(new Trade());
		rm.excuse();

	}
	
	/**
	 * ������
	 */
	public void BaseMoving() {
		System.out.println("������Ǩ�㿪ʼ...");
		RatingMoving rm = new RatingMoving(new Base());
		rm.excuse();

	}
	
	/**
	 * �ʽ���
	 */
	public void FinanMoving() {
		System.out.println("�ʽ���Ǩ�㿪ʼ...");
		RatingMoving rm = new RatingMoving(new Finan());
		rm.excuse();

	}
	
	/**
	 * ��Ϣ��
	 */
	public void InfoMoving() {
		System.out.println("��Ϣ��Ǩ�㿪ʼ...");
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
