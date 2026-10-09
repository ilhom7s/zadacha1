package org.example.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinalOrder {
    private final String name;
    private final int weigth;
    private final BigDecimal price;
    private final BigDecimal discount;
    private final BigDecimal totalAmount;

    private static final BigDecimal FULL_PRICE_RATE = BigDecimal.ONE;
    private static final int MONEY_SCALE = 2;

    public FinalOrder(BigDecimal discount, BigDecimal price, int weigth, String name) {
        this.discount = discount;
        this.price = price;
        this.weigth = weigth;
        this.name = name;
        this.totalAmount = calculateTotalAmount(discount,price,weigth);
    }

    public String getName() {
        return name;
    }

    public int getWeigth() {
        return weigth;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    private BigDecimal calculateTotalAmount(BigDecimal discount, BigDecimal price, int weight) {
        BigDecimal priceRate = FULL_PRICE_RATE.subtract(discount);
        BigDecimal weightValue = BigDecimal.valueOf(weight);

        return price.multiply(weightValue)
                .multiply(priceRate)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

}
