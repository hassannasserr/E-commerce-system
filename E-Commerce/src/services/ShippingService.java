package services;

import interfaces.Shippable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 30.0;

    public void processShipment(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;

        Map<Shippable, Integer> itemQuantities = new HashMap<>();
        for (Shippable item : items) {
            itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + 1);
        }

        for (Map.Entry<Shippable, Integer> entry : itemQuantities.entrySet()) {
            Shippable item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(String.format("%dx %-10s %6dg", quantity, item.getName(), (int) (item.getWeight() * 1000 * quantity)));
            totalWeight += item.getWeight() * quantity;
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }

    public double calculateShippingFee(double totalWeight) {
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
}
