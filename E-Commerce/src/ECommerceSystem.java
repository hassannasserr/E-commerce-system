import java.time.LocalDate;

import Model.Customer;
import Model.Product;

import services.Cart;
import services.CheckoutService;
import services.ShippingService;
import strategy.expiration.ExpirableStrategy;
import strategy.expiration.NonExpirableStrategy;
import strategy.shipping.NonShippableStrategy;
import strategy.shipping.ShippableStrategy;


public class ECommerceSystem {
    public static void main(String[] args) {
        try {
            Product cheese = new Product("Cheese", 100.0, 10,
                    new ExpirableStrategy(LocalDate.now().plusDays(30)),
                    new ShippableStrategy(0.4));

            Product tv = new Product("TV", 500.0, 5,
                    new NonExpirableStrategy(),
                    new ShippableStrategy(2.5));

            Product scratchCard = new Product("Mobile scratch card", 50.0, 20,
                    new NonExpirableStrategy(),
                    new NonShippableStrategy());

            Product biscuits = new Product("Biscuits", 150.0, 8,
                    new ExpirableStrategy(LocalDate.now().plusDays(60)),
                    new ShippableStrategy(0.7));

            Customer customer = new Customer("Hassan", 100);

            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);
            cart.add(biscuits, 1);


            ShippingService shippingService = new ShippingService();
            CheckoutService checkoutService = new CheckoutService(shippingService);

            checkoutService.checkout(customer, cart);

            System.out.println("\nCustomer balance after purchase: $" + customer.getBalance());

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    }
