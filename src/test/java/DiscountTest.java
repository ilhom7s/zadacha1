import org.example.Order.FinalOrder;
import org.example.Order.Order;
import org.example.services.Discount;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    private Order order(String name, int weight) {
        Order order = new Order();
        order.setName(name);
        order.setWeight(weight);
        return order;
    }

    @Test
    void firstOrderGetsStartDiscountNextOnesLess() {
        Discount discount = new Discount(0.5, 0.05, 10);

        List<FinalOrder> result = discount.makeDiscount(List.of(
                order("A", 2000),
                order("B", 1000)
        ));

        assertEquals(10000, result.get(0).getTotalAmount(), 0.001); // 2000 * 10 * 0.5
        assertEquals(5500, result.get(1).getTotalAmount(), 0.001);  // 1000 * 10 * 0.55
    }

    @Test
    void discountNeverGoesBelowZero() {
        Discount discount = new Discount(0.1, 0.05, 10);

        List<FinalOrder> result = discount.makeDiscount(List.of(
                order("A", 100),
                order("A", 100),
                order("A", 100),
                order("A", 100)
        ));

        assertEquals(0.0, result.get(2).getDiscount(), 0.001);
        assertEquals(0.0, result.get(3).getDiscount(), 0.001);
        assertEquals(1000, result.get(3).getTotalAmount(), 0.001);
    }
}
