import org.example.Order.FinalOrder;
import org.example.services.OrderSum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderSumTest {

    private void assertAmount(String expected, BigDecimal actual) {
        assertEquals(0, new BigDecimal(expected).compareTo(actual),
                () -> "Ожидалось " + expected + ", получено " + actual);
    }

    @Test
    void ordersOfOneCompanyAreSummed() {
        List<FinalOrder> orders = List.of(
                new FinalOrder(0.5, new BigDecimal("10"), 2000, "A"),
                new FinalOrder(0.45, new BigDecimal("20"), 1000, "B"),
                new FinalOrder(0.4, new BigDecimal("30"), 500, "A")
        );

        Map<String, BigDecimal> totals = new OrderSum().sumByCompany(orders);

        assertEquals(2, totals.size());
        assertAmount("19000", totals.get("A")); // 10000 + 9000
        assertAmount("11000", totals.get("B"));
    }
}