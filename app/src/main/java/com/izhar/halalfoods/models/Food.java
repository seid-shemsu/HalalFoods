package com.izhar.halalfoods.models;

public class Food {
    String name, id, price, rate, url, availability;

    public Food() {
    }

    public Food(String name, String id, String price, String rate, String url, String availability) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.rate = rate;
        this.url = url;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getRate() {
        return rate;
    }

    public String getUrl(){
        return url;
    }

    public String getAvailability() { return availability; }
}
