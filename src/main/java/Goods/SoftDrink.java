package Goods;

import Utils.SoftDrinkCategories;

public class SoftDrink extends Product {
    public SoftDrink(long id, String name, double price, SoftDrinkCategories category, double volume, String description, long quantity) {
        super(id, name, price, category, volume, description, quantity);
    }
}
