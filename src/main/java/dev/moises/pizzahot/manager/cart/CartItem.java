package dev.moises.pizzahot.manager.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItem {
    protected final String name;
    protected final String description;
    protected final double basePrice;
    protected final String image;

    public double calculatePrice() {
        return getBasePrice();
    }
}
