import org.example.Order.FinalOrder;
import org.example.Order.Order;
import org.example.services.Discount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    private static final double DELTA = 0.001;

    private Order order(String name, int weight) {
        Order order = new Order();
        order.setName(name);
        order.setWeight(weight);
        return order;
    }

    private void assertAmount(String expected, BigDecimal actual) {
        assertEquals(0, new BigDecimal(expected).compareTo(actual),
                () -> "Ожидалось " + expected + ", получено " + actual);
    }

    @Test
    void firstOrderGetsStartDiscountNextOnesLess() {
        Discount discount = new Discount(0.5, 0.05, new BigDecimal("10"));

        List<FinalOrder> result = discount.makeDiscount(List.of(
                order("A", 2000),
                order("B", 1000)
        ));

        assertAmount("10000", result.get(0).getTotalAmount());
        assertAmount("5500", result.get(1).getTotalAmount());
    }

    @Test
    void discountNeverGoesBelowZero() {
        Discount discount = new Discount(0.1, 0.05, new BigDecimal("10"));

        List<FinalOrder> result = discount.makeDiscount(List.of(
                order("A", 100),
                order("A", 100),
                order("A", 100),
                order("A", 100)
        ));

        assertEquals(0.0, result.get(2).getDiscount(), DELTA);
        assertEquals(0.0, result.get(3).getDiscount(), DELTA);
        assertAmount("1000", result.get(3).getTotalAmount());
    }
}