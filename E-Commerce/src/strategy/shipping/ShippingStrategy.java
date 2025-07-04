package strategy.shipping;

// Strategy Pattern for shipping behavior
public interface ShippingStrategy {
    boolean requiresShipping();

    double getWeight();
}
