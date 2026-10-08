package org.example.services;

import org.example.Order.FinalOrder;
import org.example.Order.Order;

import java.util.ArrayList;
import java.util.List;


public class Discount {
    private final double startDiscount;
    private final double step;
    private final double pricePerKg;

    public Discount(double startDiscount, double step, double pricePerKg) {
        this.startDiscount = startDiscount;
        this.step = step;
        this.pricePerKg = pricePerKg;
    }
    public List<FinalOrder> makeDiscount(List<Order> list){
        List<FinalOrder> result  = new ArrayList<>();
        double discount = startDiscount;
       for(Order order:list){

           result.add(new FinalOrder(discount,pricePerKg, order.getWeight(), order.getName()));
           double nextDiscount = discount-step;
           discount = Math.max(0,nextDiscount);

       }
       return result;
    }
}
