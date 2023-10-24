package dev.moises.pizzahot.manager.order;

import lombok.Getter;

@Getter
public class CartItem {

    private final String name;

    private final double basePrice;

    private final int size;

    public CartItem(String name, double basePrice, int size) {
        this.name = name;
        this.basePrice = basePrice;
        this.size = size;
    }

    public double calculatePrice() {
        return basePrice + getSizePrice();
    }

    private double getSizePrice() {
        return size * 30.0;
    }
}