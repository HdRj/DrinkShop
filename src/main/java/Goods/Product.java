 package Goods;

import Utils.Categories;

public abstract class Product {
    private long id;
    private String name;
    private double price;
    private Categories category;
    private double volume;
    private String description;
    private long quantity;

    private long offtake;
    private long purchasedProduct;
    private double profit;
    private double spentMoney;


    public Product(long id, String name, double price, Categories category, double volume, String description, long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.volume = volume;
        this.description = description;
        this.quantity = quantity;

        this.offtake=0;
        this.purchasedProduct=0;
        this.profit=0;
        this.spentMoney=0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    public String getDescription() {
        return description;
    }

    public long getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category.getValue();
    }

    public long getOfftake() {
        return offtake;
    }

    public long getPurchasedProduct() {
        return purchasedProduct;
    }

    public double getProfit() {
        return profit;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void increaseQuantity(int count){
        this.quantity+=count;
        this.purchasedProduct+=count;
        this.spentMoney+=count*price;

    }

    public boolean decreaseQuantity(int count){
        if(quantity>=count) {
            this.quantity -=count;
            return true;
        } else{
            return false;
        }
    }

    public void increaseOfftake(int count){
        this.offtake+=count;
    }

    public void increaseProfit(double markUp){
        this.profit+=markUp;
    }
}
