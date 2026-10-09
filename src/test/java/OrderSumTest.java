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
                () -> String.format("Ожидалось %s  получено %s" ,expected, actual));
    }

    @Test
    void ordersOfOneCompanyAreSummed() {
        List<FinalOrder> orders = List.of(
                new FinalOrder(new BigDecimal("0.5"), new BigDecimal("10"), 2000, "A"),  // Исправлено: Стринговый конструктор в кавычках
                new FinalOrder(new BigDecimal("0.45"), new BigDecimal("20"), 1000, "B"), // Исправлено: double обернут в new BigDecimal("...")
                new FinalOrder(new BigDecimal("0.4"), new BigDecimal("30"), 500, "A")   // Исправлено: double обернут в new BigDecimal("...")
        );


        Map<String, BigDecimal> totals = new OrderSum().sumByCompany(orders);

        assertEquals(2, totals.size());
        assertAmount("19000", totals.get("A")); // 10000 + 9000
        assertAmount("11000", totals.get("B"));
    }
}