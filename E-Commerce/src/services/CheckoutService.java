package services;

import Model.CartItem;
import Model.Customer;
import Model.Product;
import interfaces.Shippable;
import services.Cart;
import services.ShippingService;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(Customer customer, Cart cart) throws Exception {

        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (!product.isInStock(item.getQuantity())) {
                throw new Exception("Product out of stock: " + product.getName());
            }
            if (product.isExpired()) {
                throw new Exception("Product expired: " + product.getName());
            }
        }

        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = getShippableItems(cart);
        double totalWeight = shippableItems.stream().mapToDouble(Shippable::getWeight).sum();
        double shippingFee = shippingService.calculateShippingFee(totalWeight);
        double totalAmount = subtotal + shippingFee;

        if (!customer.hasEnoughBalance(totalAmount)) {
            throw new Exception("Insufficient balance");
        }

        customer.deductBalance(totalAmount);

        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        printReceipt(cart, subtotal, shippingFee, totalAmount);
    }

    private List<Shippable> getShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.requiresShipping()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add(product);
                }
            }
        }

        return shippableItems;
    }

    private void printReceipt(Cart cart, double subtotal, double shippingFee, double totalAmount) {
        System.out.println("\n** Checkout receipt **");

        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " +
                    (int) item.getTotalPrice());
        }

        System.out.println("----------------------");
        System.out.println("Subtotal " + (int) subtotal);
        System.out.println("Shipping " + (int) shippingFee);
        System.out.println("Amount " + (int) totalAmount);
    }
}
