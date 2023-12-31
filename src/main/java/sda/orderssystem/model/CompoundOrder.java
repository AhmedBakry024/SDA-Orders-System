package sda.orderssystem.model;

import java.util.ArrayList;
import sda.orderssystem.repository.OrdersDatabase;

public class CompoundOrder extends Order {


    private ArrayList<SimpleOrder> orders;
    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();

    // this is the constructor that will be used to create a compound order
    public CompoundOrder(ArrayList<SimpleOrder> o) {
        this.orders = new ArrayList<>();
        for (Order order : o) {
            this.orders.add(new SimpleOrder(order));
        }
        this.id = ordersDatabase.ordersDatabase.size();
    }
    
    public CompoundOrder() {
        super();
    }

    // these functions are the ones that are needed to implement the composite pattern

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
