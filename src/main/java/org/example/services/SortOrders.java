package org.example.services;

import org.example.Order.Order;
import org.example.Order.OrderSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortOrders {

public  List<Order> sortOrders(List<OrderSource> listOfOrders){
    List<Order> sortedList = new ArrayList<>();
    for(OrderSource source:listOfOrders){
        sortedList.addAll(source.getOrders());
    }
     sortedList.sort(Comparator.comparing(Order::getDateTime));
    return sortedList;
}



}
