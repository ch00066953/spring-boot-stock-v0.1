package com.ch.pojo;

public class StockInfo {

	private String id;

    private String compabbre;

    private String compname;

    private String englishname;

    private String regadd;

    private String sharescode;

    private String sharesaddre;

    private String timemarket;

    private String totalequity;

    private String flowequity;

    private String bsharescode;

    private String bsharesaddre;

    private String btimemarket;

    private String btotalequity;

    private String bflowequity;

    private String area;

    private String province;

    private String city;

    private String website;

    private String industry;

    private String industry2;

    private String industry3;

    private String status;

    public StockInfo() {
    	
    }
    public StockInfo(String id) {
    	setId(id);
	}
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompabbre() {
        return compabbre;
    }

    public void setCompabbre(String compabbre) {
        this.compabbre = compabbre == null ? null : compabbre.trim();
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname == null ? null : compname.trim();
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname == null ? null : englishname.trim();
    }

    public String getRegadd() {
        return regadd;
    }

    public void setRegadd(String regadd) {
        this.regadd = regadd == null ? null : regadd.trim();
    }

    public String getSharescode() {
        return sharescode;
    }

    public void setSharescode(String sharescode) {
        this.sharescode = sharescode == null ? null : sharescode.trim();
    }

    public String getSharesaddre() {
        return sharesaddre;
    }

    public void setSharesaddre(String sharesaddre) {
        this.sharesaddre = sharesaddre == null ? null : sharesaddre.trim();
    }

    public String getTimemarket() {
        return timemarket;
    }

    public void setTimemarket(String timemarket) {
        this.timemarket = timemarket == null ? null : timemarket.trim();
    }

    public String getTotalequity() {
        return totalequity;
    }

    public void setTotalequity(String totalequity) {
        this.totalequity = totalequity == null ? null : totalequity.trim();
    }

    public String getFlowequity() {
        return flowequity;
    }

    public void setFlowequity(String flowequity) {
        this.flowequity = flowequity == null ? null : flowequity.trim();
    }

    public String getBsharescode() {
        return bsharescode;
    }

    public void setBsharescode(String bsharescode) {
        this.bsharescode = bsharescode == null ? null : bsharescode.trim();
    }

    public String getBsharesaddre() {
        return bsharesaddre;
    }

    public void setBsharesaddre(String bsharesaddre) {
        this.bsharesaddre = bsharesaddre == null ? null : bsharesaddre.trim();
    }

    public String getBtimemarket() {
        return btimemarket;
    }

    public void setBtimemarket(String btimemarket) {
        this.btimemarket = btimemarket == null ? null : btimemarket.trim();
    }

    public String getBtotalequity() {
        return btotalequity;
    }

    public void setBtotalequity(String btotalequity) {
        this.btotalequity = btotalequity == null ? null : btotalequity.trim();
    }

    public String getBflowequity() {
        return bflowequity;
    }

    public void setBflowequity(String bflowequity) {
        this.bflowequity = bflowequity == null ? null : bflowequity.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getIndustry2() {
        return industry2;
    }

    public void setIndustry2(String industry2) {
        this.industry2 = industry2 == null ? null : industry2.trim();
    }

    public String getIndustry3() {
        return industry3;
    }

    public void setIndustry3(String industry3) {
        this.industry3 = industry3 == null ? null : industry3.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}