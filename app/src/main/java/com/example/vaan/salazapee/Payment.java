package com.example.vaan.salazapee;

public class Payment {
    private String name;
    private int sumnum,sumcost,cost;

    public Payment(String name,int cost, int sumnum, int sumcost) {
        this.name = name;
        this.cost = cost;
        this.sumnum = sumnum;
        this.sumcost = sumcost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumnum() {
        return sumnum;
    }

    public void setSumnum(int sumnum) {
        this.sumnum = sumnum;
    }

    public int getSumcost() {
        return sumcost;
    }

    public void setSumcost(int sumcost) {
        this.sumcost = sumcost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
