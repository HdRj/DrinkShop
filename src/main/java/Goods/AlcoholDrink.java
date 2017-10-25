package Goods;

import Utils.AlcoholDrinkCategories;

public class AlcoholDrink extends Product {

    private double ABV;

    public AlcoholDrink(long id, String name, double price, AlcoholDrinkCategories category, double volume, String description, long quantity) {
         super(id, name, price, category, volume, description, quantity);
         ABV=Double.parseDouble(description);
    }

    public double getABV(){
        return ABV;
    }
}
