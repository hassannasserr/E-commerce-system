package strategy.shipping;

import strategy.shipping.ShippingStrategy;

public class ShippableStrategy implements ShippingStrategy {
    private double weight;

    public ShippableStrategy(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
