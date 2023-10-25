package dev.moises.pizzahot.manager.cart;

import lombok.Getter;

@Getter
public class PizzaCartItem extends CartItem {

    protected final int size;

    public PizzaCartItem(String name, String description, double basePrice, String image, int size) {
        super(name, description, basePrice, image);
        this.size = size;
    }

    public double calculatePrice() {
        return getBasePrice() + getSizePrice();
    }

    private double getSizePrice() {
        return getSize() * 30.0;
    }
}