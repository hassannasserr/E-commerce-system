package Model;

import interfaces.Shippable;
import strategy.expiration.ExpirationStrategy;
import strategy.shipping.ShippingStrategy;

public class Product implements Shippable {
    private String name;
    private double price;
    private int quantity;
    private ExpirationStrategy expirationStrategy;
    private ShippingStrategy shippingStrategy;

    public Product(String name, double price, int quantity,
                   ExpirationStrategy expirationStrategy,
                   ShippingStrategy shippingStrategy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationStrategy = expirationStrategy;
        this.shippingStrategy = shippingStrategy;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public int getQuantity() { return quantity; }


    public boolean isExpired() {
        return expirationStrategy.isExpired();
    }

    public boolean requiresShipping() {
        return shippingStrategy.requiresShipping();
    }

    public double getWeight() {
        return shippingStrategy.getWeight();
    }



    public boolean isInStock(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }

    public void reduceQuantity(int amount) {
        quantity -= amount;
    }
}