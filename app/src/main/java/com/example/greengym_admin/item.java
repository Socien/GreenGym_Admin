package com.example.greengym_admin;

public class item {

    public item(String park, String date) {
        this.park = park;
        this.date = date;
    }

    private String park;
    private String date;

    public item(String park) {
        this.park = park;
    }

    public String getDate(){
        return date;
    }

    public String getPark() {
        return park;
    }
}