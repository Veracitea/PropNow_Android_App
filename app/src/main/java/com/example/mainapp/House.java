package com.example.mainapp;

public class House {

    private int id;
    private String town;
    private int flat_type;
    private String block;
    private String street_name;
    private String storey_range;
    private int floor_area_sqm;
    private String flat_model;
    private int lease_commence_date;
    private String remaining_lease;
    private int resale_price;
    private int agent_id;

    //constructors

    public House(int id, String town, int flat_type, String block, String street_name,
                 String storey_range, int floor_area_sqm, String flat_model, int lease_commence_date,
                 String remaining_lease, int resale_price, int agent_id) {
        this.id = id;
        this.town = town;
        this.flat_type = flat_type;
        this.block = block;
        this.street_name = street_name;
        this.storey_range = storey_range;
        this.floor_area_sqm = floor_area_sqm;
        this.flat_model = flat_model;
        this.lease_commence_date = lease_commence_date;
        this.remaining_lease = remaining_lease;
        this.resale_price = resale_price;
        this.agent_id = agent_id;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", town='" + town + '\'' +
                ", flat_type=" + flat_type +
                ", block='" + block + '\'' +
                ", street_name='" + street_name + '\'' +
                ", storey_range='" + storey_range + '\'' +
                ", floor_area_sqm='" + floor_area_sqm + '\'' +
                ", flat_model='" + flat_model + '\'' +
                ", lease_commence_date=" + lease_commence_date +
                ", remaining_lease='" + remaining_lease + '\'' +
                ", resale_price=" + resale_price +
                ", agent_id=" + agent_id +
                '}';
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getFlat_type() {
        return flat_type;
    }

    public void setFlat_type(int flat_type) {
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
        this.remaining_lease = remaining_lease + " years";
    }

    public int getResale_price() {
        return resale_price;
    }

    public void setResale_price(int resale_price) {
        this.resale_price = resale_price;
    }

    public int getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(int agent_id) {
        this.agent_id = agent_id;
    }
}
