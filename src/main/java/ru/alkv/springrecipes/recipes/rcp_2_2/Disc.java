package ru.alkv.springrecipes.recipes.rcp_2_2;

public class Disc extends Product {
    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price, double discount) {
        super(name, price, discount);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }	
}
