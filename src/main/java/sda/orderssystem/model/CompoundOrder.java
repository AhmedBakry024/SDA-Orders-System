package sda.orderssystem.model;

import java.util.ArrayList;

public class CompoundOrder extends Order {
    private ArrayList<Order> orders;

    public CompoundOrder(Order[] orders) {
        
    }

    @Override
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public void removeOrder(Order order) {
       this.orders.remove(order);
    }

    @Override
    public Order getChild(int i) {
        return this.orders.get(i);
    }
}
