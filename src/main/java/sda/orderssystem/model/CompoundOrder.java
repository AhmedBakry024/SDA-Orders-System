package sda.orderssystem.model;

import java.util.ArrayList;

public class CompoundOrder extends Order {
    private ArrayList<SimpleOrder> orders;

    public CompoundOrder(ArrayList<SimpleOrder> orders) {

        for (Order order : orders) {
            this.orders.add(new SimpleOrder(order));
        }
        this.id = orders.get(0).getId();
    }
    
    public CompoundOrder() {
        super();
    }

    @Override
    public void addOrder(SimpleOrder order) {
        this.orders.add(order);
    }

    @Override
    public void removeOrder(SimpleOrder order) {
       this.orders.remove(order);
    }

    @Override
    public Order getChild(int i) {
        return this.orders.get(i);
    }

    @Override
    public ArrayList<SimpleOrder> getChildren() {
        return this.orders;
    }
}
