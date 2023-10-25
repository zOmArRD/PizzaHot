package dev.moises.pizzahot.manager.client;

import dev.moises.pizzahot.manager.cart.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private String name;
    private String lastName;
    private String fullAddress;
    private String phoneNumber;
    private ShoppingCart shoppingCart;
}
