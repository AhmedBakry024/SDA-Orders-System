package sda.orderssystem.model;

import java.text.ParseException;
import java.util.ArrayList;
import sda.orderssystem.repository.OrdersDatabase;

public class CompoundOrder extends Order {
    private ArrayList<SimpleOrder> orders;
    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();


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
