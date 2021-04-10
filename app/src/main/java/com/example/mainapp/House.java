package com.example.mainapp;

public class House {
    private int id;
    private int noOfRooms;
    private String blk;
    private String unitNo;
    private String nearestMRT;

    //constructors


    public House(int id, int noOfRooms, String blk, String unitNo, String nearestMRT) {
        this.id = id;
        this.noOfRooms = noOfRooms;
        this.blk = blk;
        this.unitNo = unitNo;
        this.nearestMRT = nearestMRT;
    }

    @Override
    public String toString() {
        return "house{" +
                "id=" + id +
                ", noOfRooms=" + noOfRooms +
                ", blk='" + blk + '\'' +
                ", unitNo='" + unitNo + '\'' +
                ", nearestMRT='" + nearestMRT + '\'' +
                '}';
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getBlk() {
        return blk;
    }

    public void setBlk(String blk) {
        this.blk = blk;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getNearestMRT() {
        return nearestMRT;
    }

    public void setNearestMRT(String nearestMRT) {
        this.nearestMRT = nearestMRT;
    }
}
