package dev.moises.pizzahot.manager.items;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
    private final String name;
    private final double price;
    private final String image;
}
