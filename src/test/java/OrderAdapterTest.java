import org.example.Order.Order;
import org.example.Readers.Reader;
import org.example.adapters.OrderAdapter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderAdapterTest {

    @Test
    void convertsLineToOrder() {
        Reader reader = mock(Reader.class);
        when(reader.read()).thenReturn(List.<String[]>of(
                new String[]{"2026-03-15T10:00:00", " A ", " 2000 "}
        ));

        List<Order> orders = new OrderAdapter(reader).getOrders();

        assertEquals(1, orders.size());
        Order order = orders.get(0);
        assertEquals(LocalDateTime.of(2026, 3, 15, 10, 0), order.getDateTime());
        assertEquals("A", order.getName());
        assertEquals(2000, order.getWeight());

        verify(reader, times(1)).read(); // адаптер прочитал данные ровно один раз
    }

    @Test
    void emptyReaderGivesEmptyList() {
        Reader reader = mock(Reader.class);
        when(reader.read()).thenReturn(List.of());

        List<Order> orders = new OrderAdapter(reader).getOrders();

        assertTrue(orders.isEmpty());
    }
}
