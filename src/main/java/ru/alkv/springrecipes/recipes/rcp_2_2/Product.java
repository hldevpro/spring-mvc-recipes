package ru.alkv.springrecipes.recipes.rcp_2_2;

import java.text.DecimalFormat;

public class Product {

    private String name;
    private double price;
    private double discount;

    public Product() {
    }

    public Product(String name, double price, double discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#%");

        return name + " " + price + " (" + df.format(discount) + " discount!)";
    }	
}
