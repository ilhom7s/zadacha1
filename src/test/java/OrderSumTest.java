import org.example.Order.FinalOrder;
import org.example.services.OrderSum;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderSumTest {

    @Test
    void ordersOfOneCompanyAreSummed() {
        List<FinalOrder> orders = List.of(
                new FinalOrder(0.5, 10, 2000, "A"),
                new FinalOrder(0.45, 10, 1000, "B"),
                new FinalOrder(0.4, 10, 500, "A")
        );

        Map<String, Double> totals = new OrderSum().sumByCompany(orders);

        assertEquals(2, totals.size());
        assertEquals(13000, totals.get("A"), 0.001);
        assertEquals(5500, totals.get("B"), 0.001);
    }
}
