package org.example.adapters;

import org.example.Order.Order;
import org.example.Order.OrderSource;
import org.example.Readers.Reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter implements OrderSource {
    private final Reader reader;
    public static final int EXPECTED_PARTS = 3;
    public static final int DATE_TIME_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int WEIGHT_INDEX = 2;
    public OrderAdapter(Reader reader) {
        this.reader = reader;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> result = new ArrayList<>();
        for (String[] parts : reader.read()) {

            if(parts.length < EXPECTED_PARTS){
                throw new RuntimeException(String.format("Получен массив размером меньше %d",EXPECTED_PARTS));
            }

            Order order = new Order();
            order.setDateTime( LocalDateTime.parse(parts[ DATE_TIME_INDEX ].trim()));
            order.setName(parts[NAME_INDEX].trim());
            order.setWeight(Integer.parseInt(parts[WEIGHT_INDEX].trim()));
            result.add(order);

        }
        return result;
    }
}
