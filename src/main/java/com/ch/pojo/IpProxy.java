package com.ch.pojo;

public class IpProxy {
    private String ip;

    private String port;

    private String type;

    private String anonymity;

    private String belong;

    private String operator;

    private String speed;

    private String prooftime;

    private String fristtime;

    private String status;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity == null ? null : anonymity.trim();
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed == null ? null : speed.trim();
    }

    public String getProoftime() {
        return prooftime;
    }

    public void setProoftime(String prooftime) {
        this.prooftime = prooftime == null ? null : prooftime.trim();
    }

    public String getFristtime() {
        return fristtime;
    }

    public void setFristtime(String fristtime) {
        this.fristtime = fristtime == null ? null : fristtime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}