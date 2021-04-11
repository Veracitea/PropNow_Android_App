package com.example.mainapp;

public class databass3 {
    private int agent;
    private string month;
    private string town;
    private string flat_type;
    private string block;
    private string street_name;
    private string storey_range;
    private int floor_area_sqm;
    private string flat_model;
    private int lease_commence_date;
    private string remaining_lease;
    private int resale_price;

    public int getAgent() {
        return agent;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    public string getMonth() {
        return month;
    }

    public void setMonth(string month) {
        this.month = month;
    }

    public string getTown() {
        return town;
    }

    public void setTown(string town) {
        this.town = town;
    }

    public string getFlat_type() {
        return flat_type;
    }

    public void setFlat_type(string flat_type) {
        this.flat_type = flat_type;
    }

    public string getBlock() {
        return block;
    }

    public void setBlock(string block) {
        this.block = block;
    }

    public string getStreet_name() {
        return street_name;
    }

    public void setStreet_name(string street_name) {
        this.street_name = street_name;
    }

    public string getStorey_range() {
        return storey_range;
    }

    public void setStorey_range(string storey_range) {
        this.storey_range = storey_range;
    }

    public int getFloor_area_sqm() {
        return floor_area_sqm;
    }

    public void setFloor_area_sqm(int floor_area_sqm) {
        this.floor_area_sqm = floor_area_sqm;
    }

    public string getFlat_model() {
        return flat_model;
    }

    public void setFlat_model(string flat_model) {
        this.flat_model = flat_model;
    }

    public int getLease_commence_date() {
        return lease_commence_date;
    }

    public void setLease_commence_date(int lease_commence_date) {
        this.lease_commence_date = lease_commence_date;
    }

    public string getRemaining_lease() {
        return remaining_lease;
    }

    public void setRemaining_lease(string remaining_lease) {
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
