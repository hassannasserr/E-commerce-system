package strategy.shipping;

import strategy.shipping.ShippingStrategy;

public class NonShippableStrategy implements ShippingStrategy {
    @Override
    public boolean requiresShipping() {
        return false;
    }

    @Override
    public double getWeight() {
        return 0.0;
    }
}
