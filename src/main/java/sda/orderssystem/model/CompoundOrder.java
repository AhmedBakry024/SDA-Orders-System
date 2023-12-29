package sda.orderssystem.model;

import java.util.ArrayList;

public class CompoundOrder extends Order {
    private ArrayList<SimpleOrder> orders;

    public CompoundOrder(ArrayList<SimpleOrder> Oh) {
        this.orders = new ArrayList<>();
        for (Order order : Oh) {
            this.orders.add(new SimpleOrder(order));
        }
        this.id = Oh.get(0).getId();
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
