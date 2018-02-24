package com.ch.domain;

import java.io.Serializable;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.annotation.Id;



/**
 * The persistent class for the stock_info database table.
 * 
 */
@NamedQuery(name="StockInfo.findAll", query="SELECT s FROM StockInfo s")
public class StockInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String area;

	private String BFlowEquity;

	private String BSharesAddre;

	private String BSharesCode;

	private String BTimeMarket;

	private String BTotalEquity;

	private String city;

	private String compAbbre;

	private String compName;

	private String englishName;

	private String flowEquity;

	private String industry;

	private String industry2;

	private String industry3;

	private String province;

	private String regAdd;

	private String sharesAddre;

	private String sharesCode;

	private String timeMarket;

	private String totalEquity;

	private String website;

	public StockInfo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBFlowEquity() {
		return this.BFlowEquity;
	}

	public void setBFlowEquity(String BFlowEquity) {
		this.BFlowEquity = BFlowEquity;
	}

	public String getBSharesAddre() {
		return this.BSharesAddre;
	}

	public void setBSharesAddre(String BSharesAddre) {
		this.BSharesAddre = BSharesAddre;
	}

	public String getBSharesCode() {
		return this.BSharesCode;
	}

	public void setBSharesCode(String BSharesCode) {
		this.BSharesCode = BSharesCode;
	}

	public String getBTimeMarket() {
		return this.BTimeMarket;
	}

	public void setBTimeMarket(String BTimeMarket) {
		this.BTimeMarket = BTimeMarket;
	}

	public String getBTotalEquity() {
		return this.BTotalEquity;
	}

	public void setBTotalEquity(String BTotalEquity) {
		this.BTotalEquity = BTotalEquity;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompAbbre() {
		return this.compAbbre;
	}

	public void setCompAbbre(String compAbbre) {
		this.compAbbre = compAbbre;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getFlowEquity() {
		return this.flowEquity;
	}

	public void setFlowEquity(String flowEquity) {
		this.flowEquity = flowEquity;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIndustry2() {
		return this.industry2;
	}

	public void setIndustry2(String industry2) {
		this.industry2 = industry2;
	}

	public String getIndustry3() {
		return this.industry3;
	}

	public void setIndustry3(String industry3) {
		this.industry3 = industry3;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegAdd() {
		return this.regAdd;
	}

	public void setRegAdd(String regAdd) {
		this.regAdd = regAdd;
	}

	public String getSharesAddre() {
		return this.sharesAddre;
	}

	public void setSharesAddre(String sharesAddre) {
		this.sharesAddre = sharesAddre;
	}

	public String getSharesCode() {
		return this.sharesCode;
	}

	public void setSharesCode(String sharesCode) {
		this.sharesCode = sharesCode;
	}

	public String getTimeMarket() {
		return this.timeMarket;
	}

	public void setTimeMarket(String timeMarket) {
		this.timeMarket = timeMarket;
	}

	public String getTotalEquity() {
		return this.totalEquity;
	}

	public void setTotalEquity(String totalEquity) {
		this.totalEquity = totalEquity;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}