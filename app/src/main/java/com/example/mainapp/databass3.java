package com.example.mainapp;

public class databass3 {
    private int agent;
    private String month;
    private String town;
    private String flat_type;
    private String block;
    private String street_name;
    private String storey_range;
    private int floor_area_sqm;
    private String flat_model;
    private int lease_commence_date;
    private String remaining_lease;
    private int resale_price;

    public int getAgent() {
        return agent;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getFlat_type() {
        return flat_type;
    }

    public void setFlat_type(String flat_type) {
        this.flat_type = flat_type;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getStorey_range() {
        return storey_range;
    }

    public void setStorey_range(String storey_range) {
        this.storey_range = storey_range;
    }

    public int getFloor_area_sqm() {
        return floor_area_sqm;
    }

    public void setFloor_area_sqm(int floor_area_sqm) {
        this.floor_area_sqm = floor_area_sqm;
    }

    public String getFlat_model() {
        return flat_model;
    }

    public void setFlat_model(String flat_model) {
        this.flat_model = flat_model;
    }

    public int getLease_commence_date() {
        return lease_commence_date;
    }

    public void setLease_commence_date(int lease_commence_date) {
        this.lease_commence_date = lease_commence_date;
    }

    public String getRemaining_lease() {
        return remaining_lease;
    }

    public void setRemaining_lease(String remaining_lease) {
        this.remaining_lease = remaining_lease;
    }

    public int getResale_price() {
        return resale_price;
    }

    public void setResale_price(int resale_price) {
        this.resale_price = resale_price;
    }

    @Override
    public String toString() {
        return "databass3{" +
                "agent=" + agent +
                ", month=" + month +
                ", town=" + town +
                ", flat_type=" + flat_type +
                ", block=" + block +
                ", street_name=" + street_name +
                ", storey_range=" + storey_range +
                ", floor_area_sqm=" + floor_area_sqm +
                ", flat_model=" + flat_model +
                ", lease_commence_date=" + lease_commence_date +
                ", remaining_lease=" + remaining_lease +
                ", resale_price=" + resale_price +
                '}';
    }
}
