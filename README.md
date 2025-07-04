# E-Commerce System

A simple Java e-commerce system built to practice design patterns. Handles shopping cart functionality with expiration dates and shipping calculations.

## What it does

Command-line e-commerce system where customers can add products to a cart and checkout. Uses Strategy pattern for different product types - some expire, some don't, some need shipping, others are digital.

## How to run

You'll need Java 8+:

```bash
javac -d out src/**/*.java
java -cp out ECommerceSystem
```

The main method has a working example that processes a complete order.

## Key Features

- Products can expire or never expire
- Physical products need shipping, digital ones don't
- Basic inventory tracking
- Shopping cart validation
- Automatic shipping calculation based on weight
- Receipt generation

## Project Structure

```
src/
├── interfaces/          # Core contracts
├── Model/              # Product, Customer, Cart entities
├── services/           # Checkout and shipping logic
├── strategy/           # Strategy implementations
│   ├── expiration/     # Expiration behaviors
│   └── shipping/       # Shipping behaviors
└── ECommerceSystem.java # Main class
```

## Example Usage

```java
// Create products with different strategies
Product cheese = new Product("Cheese", 100.0, 10, 
                           new ExpirableStrategy(LocalDate.now().plusDays(30)), 
                           new ShippableStrategy(0.4));

Product digitalCard = new Product("Mobile scratch card", 50.0, 20, 
                                new NonExpirableStrategy(), 
                                new NonShippableStrategy());

// Shopping flow
Customer customer = new Customer("John", 1000.0);
Cart cart = new Cart();
cart.add(cheese, 2);
cart.add(digitalCard, 1);

// Checkout
CheckoutService checkout = new CheckoutService(new ShippingService());
checkout.checkout(customer, cart);
```

## Configuration

Shipping rate is $30 per kg in `ShippingService.java`. Change this value if needed.
