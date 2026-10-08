package org.example.adapters;

import org.example.Order.Order;
import org.example.Order.OrderSource;
import org.example.Readers.Reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter implements OrderSource {
    private final Reader reader;

    public OrderAdapter(Reader reader) {
        this.reader = reader;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> result = new ArrayList<>();
        for (String[] parts : reader.read()) {
            Order order = new Order();
            order.setDateTime(LocalDateTime.parse(parts[0].trim()));
            order.setName(parts[1].trim());
            order.setWeight(Integer.parseInt(parts[2].trim()));
            result.add(order);
        }
        return result;
    }
}
