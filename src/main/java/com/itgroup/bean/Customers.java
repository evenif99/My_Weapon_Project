package com.itgroup.bean;

public class Customers {
    private String cid;
    private String country;
    private int ordervolume;
    private int budget;
    private String contractdate;

    public Customers() {
    }

    public Customers(String cid, String country, int ordervolume, int budget, String contractdate) {
        this.cid = cid;
        this.country = country;
        this.ordervolume = ordervolume;
        this.budget = budget;
        this.contractdate = contractdate;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "cid='" + cid + '\'' +
                ", country='" + country + '\'' +
                ", ordervolume=" + ordervolume +
                ", budget=" + budget +
                ", contractdate='" + contractdate + '\'' +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getOrdervolume() {
        return ordervolume;
    }

    public void setOrdervolume(int ordervolume) {
        this.ordervolume = ordervolume;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getContractdate() {
        return contractdate;
    }

    public void setContractdate(String contractdate) {
        this.contractdate = contractdate;
    }
}
