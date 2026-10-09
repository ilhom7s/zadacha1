package org.example.services;

import org.example.Order.FinalOrder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSum {

    public Map<String, BigDecimal> sumByCompany(List<FinalOrder> orders) {
        Map<String, BigDecimal> totals = new HashMap<>();

        for (FinalOrder order : orders) {

            String name = order.getName();
            BigDecimal amount = order.getTotalAmount();

            if (totals.containsKey(name)) {
                totals.merge(name, amount, (oldAmount, newAmount) -> oldAmount.add(newAmount));
            }
            else {
                totals.put(name, amount);
            }
        }
        return totals;
    }
}