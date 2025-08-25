package com.itgroup.bean;

public class Tanks {
    private String wid;
    private String wname;
    private String cp;
    private int price;
    private String releasedate;
    private String calibers;
    private int amount;

    public Tanks() {
    }

    public Tanks(String wid, String wname, String cp, int price, String releasedate, String calibers, int amount) {
        this.wid = wid;
        this.wname = wname;
        this.cp = cp;
        this.price = price;
        this.releasedate = releasedate;
        this.calibers = calibers;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Tanks{" +
                "wid='" + wid + '\'' +
                ", wname='" + wname + '\'' +
                ", cp='" + cp + '\'' +
                ", price=" + price +
                ", releasedate='" + releasedate + '\'' +
                ", calibers='" + calibers + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getCalibers() {
        return calibers;
    }

    public void setCalibers(String calibers) {
        this.calibers = calibers;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
