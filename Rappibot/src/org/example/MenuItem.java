package org.example;

public class MenuItem {
    private String name;
    private double price;
    private int preparationTime;

    // Constructor con parámetros
    public MenuItem(String name, double price, int preparationTime) {
        this.name = name;
        this.price = price;
        this.preparationTime = preparationTime;
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

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }
}