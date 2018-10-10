package com.ch.pojo;

public class UserWebCollection {
    private Integer id;

    private String title;

    private String website;

    private String mainstie;

    private String iconsite;

    private String webtitle;

    private String description;

    private String keywords;

    private String userid;

    private String usertype;

    private String systemtype;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getMainstie() {
        return mainstie;
    }

    public void setMainstie(String mainstie) {
        this.mainstie = mainstie == null ? null : mainstie.trim();
    }

    public String getIconsite() {
        return iconsite;
    }

    public void setIconsite(String iconsite) {
        this.iconsite = iconsite == null ? null : iconsite.trim();
    }

    public String getWebtitle() {
        return webtitle;
    }

    public void setWebtitle(String webtitle) {
        this.webtitle = webtitle == null ? null : webtitle.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype == null ? null : systemtype.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}