package org.example.Order;

import org.example.Constants;

public class FinalOrder {
    private final String name;
    private final int weigth;
    private final double price;
    private final double discount;
private  final double totalAmount;

    public FinalOrder(double discount, double price, int weigth, String name) {
        this.discount = discount;
        this.price = price;
        this.weigth = weigth;
        this.name = name;
        this.totalAmount = solvetotalAmount(discount,price,weigth);
    }

    public String getName() {
        return name;
    }

    public int getWeigth() {
        return weigth;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public  double solvetotalAmount(double discount, double price, int weigth){
       return( (weigth*price)*(Constants.total-discount));

    }
    @Override
    public String toString() {
        return "FinalOrder{" +
                "name='" + name + '\'' +
                ", weigth=" + weigth +
                ", price=" + price +
                ", discount=" + discount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
