import org.example.Order.Order;
import org.example.Order.OrderSource;
import org.example.services.SortOrders;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SortOrdersTest {

    private Order order(String name, String time) {
        Order order = new Order();
        order.setName(name);
        order.setDateTime(LocalDateTime.parse(time));
        return order;
    }

    @Test
    void ordersFromAllSourcesAreSortedByTime() {
        OrderSource first = mock(OrderSource.class);
        OrderSource second = mock(OrderSource.class);
        when(first.getOrders()).thenReturn(List.of(order("A", "2026-03-15T12:00:00")));
        when(second.getOrders()).thenReturn(List.of(order("B", "2026-03-15T10:00:00")));

        List<Order> sorted = new SortOrders().sortOrders(List.of(first, second));

        assertEquals(2, sorted.size());
        assertEquals("B", sorted.get(0).getName());
        assertEquals("A", sorted.get(1).getName());

        verify(first).getOrders();
        verify(second).getOrders();
    }
}
