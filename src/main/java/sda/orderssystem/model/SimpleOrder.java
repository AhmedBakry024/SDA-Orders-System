package sda.orderssystem.model;

import sda.orderssystem.repository.*;
import java.util.ArrayList;
import java.util.Calendar;

// this is the class that will be used to create a simple order (a leaf in the composite pattern)
public class SimpleOrder extends Order {

    public UsersDatabase usersDatabase = UsersDatabase.getInstance();
    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();

    // this is the constructor that will be used to create a simple order
    public SimpleOrder(Order order) {
        super();
        this.address = order.getAddress();
        this.products = order.getProductByID(order);
        this.totalPrice = order.calculateTotal(this.products);
        this.setCustomerID(order.getCustomerID());
        this.status = "Placed";
        this.id = ordersDatabase.ordersDatabase.size();
        this.date = Calendar.getInstance().getTime();
    }

    public SimpleOrder() {
        super();
    }

    // these functions are the ones that are needed to implement the composite pattern
    // but they are not implemented in this class because it is a leaf

    @Override
    public Order getChild(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'getChild'");
    }

    @Override
    public void addOrder(SimpleOrder order) {
        throw new UnsupportedOperationException("Unimplemented method 'addOrder'");
    }

    @Override
    public void removeOrder(SimpleOrder order) {
        throw new UnsupportedOperationException("Unimplemented method 'removeOrder'");
    }

    @Override
    public ArrayList<SimpleOrder> getChildren() {
        throw new UnsupportedOperationException("Unimplemented method 'getChildren'");
    }
}
