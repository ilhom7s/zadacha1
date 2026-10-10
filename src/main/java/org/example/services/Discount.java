package org.example.services;

import org.example.Order.FinalOrder;
import org.example.Order.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Discount {
    private final BigDecimal startDiscount;
    private final BigDecimal step;
    private final BigDecimal pricePerKg;
    private static final BigDecimal CONSTANT_DISCOUNT = BigDecimal.ZERO;

    public Discount(BigDecimal startDiscount, BigDecimal step, BigDecimal pricePerKg) {
        this.startDiscount = startDiscount;
        this.step = step;
        this.pricePerKg = pricePerKg;
    }
    public List<FinalOrder> makeDiscount(List<Order> orderList){

        List<FinalOrder> result  = new ArrayList<>();
        BigDecimal discount = startDiscount;

       for(Order order:orderList){
           result.add(new FinalOrder(discount,pricePerKg, order.getWeight(), order.getName()));
           BigDecimal nextDiscount = discount.subtract(step);
           discount = CONSTANT_DISCOUNT.max(nextDiscount);
       }

       return result;
    }
}
