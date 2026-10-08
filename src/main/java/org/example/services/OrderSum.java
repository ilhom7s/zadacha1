package org.example.services;

import org.example.Order.FinalOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSum {
    public Map<String, Double> sumByCompany(List<FinalOrder> orders) {
        Map<String, Double> totals = new HashMap<>();
        for (FinalOrder order : orders) {
            String name = order.getName();
            double amount = order.getTotalAmount();
            if (totals.containsKey(name)) {
                totals.put(name, totals.get(name) + amount);
            } else {
                totals.put(name, amount);
            }
        }
        return totals;
    }
}