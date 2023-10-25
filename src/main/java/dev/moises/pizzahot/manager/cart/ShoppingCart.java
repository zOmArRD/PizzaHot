package dev.moises.pizzahot.manager.cart;

import lombok.Getter;


import java.util.HashMap;
import java.util.Map;

@Getter
public class ShoppingCart {
    private final Map<CartItem, Integer> items = new HashMap<>();

    public void addItem(CartItem item) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + 1);
        } else {
            items.put(item, 1);
        }
    }

    public void removeItem(CartItem item) {
        if (items.containsKey(item)) {
            if (items.get(item) > 1) {
                items.put(item, items.get(item) - 1);
            } else {
                items.remove(item);
            }
        }
    }

    public void clearAllItems() {
        items.clear();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;

        for (Map.Entry<CartItem, Integer> entry : items.entrySet()) {
            totalPrice += entry.getKey().calculatePrice() * entry.getValue();
        }

        return Math.round(totalPrice * 100.0) / 100.0;
    }
}