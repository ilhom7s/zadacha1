package org.example.services;

import org.example.Order.FinalOrder;
import org.example.Order.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Discount {
    private final double startDiscount;
    private final double step;
    private final BigDecimal pricePerKg;
    private static final int CONSTANT_DISCOUNT = 0;

    public Discount(double startDiscount, double step, BigDecimal pricePerKg) {
        this.startDiscount = startDiscount;
        this.step = step;
        this.pricePerKg = pricePerKg;
    }
    public List<FinalOrder> makeDiscount(List<Order> orderList){
        List<FinalOrder> result  = new ArrayList<>();
        double discount = startDiscount;
       for(Order order:orderList){
           result.add(new FinalOrder(discount,pricePerKg, order.getWeight(), order.getName()));
           double nextDiscount = discount-step;
           discount = Math.max(CONSTANT_DISCOUNT,nextDiscount);

       }
       return result;
    }
}
