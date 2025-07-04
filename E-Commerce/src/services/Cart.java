package services;

import Model.CartItem;
import Model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) throws Exception {
        if (!product.isInStock(quantity)) {
            throw new Exception("Insufficient stock for product: " + product.getName());
        }

        if (product.isExpired()) {
            throw new Exception("Product is expired: " + product.getName());
        }

        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
