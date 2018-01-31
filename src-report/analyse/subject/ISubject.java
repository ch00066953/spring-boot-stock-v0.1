package analyse.subject;

import report.EReport;

public interface ISubject {

	/**
	 * 获取科目对象
	 * @param o
	 */
	public void setObject(EReport er);
	/**
	 * 三年连续增长
	 * @return
	 */
	public boolean isAdvance3y();
	
	/**
	 * 连续三年保持
	 * @return
	 */
	public boolean isKeep3y();
	
	/**
	 * 三年平均
	 * @return
	 */
	public double getMean3y();
	
	/**
	 * 获取今年预期值
	 * @return
	 */
	public double getExpect();
	
	/**
	 * 三年平均差
	 * @return
	 */
	public double getMeanDif3y();
	
	/**
	 * 三年平均增长率
	 * @return
	 */
	public double getMeanRiseRate3y();
	
	/**
	 * 是否到达某指标
	 * @return
	 */
	public boolean  isEnough(double i);
}
