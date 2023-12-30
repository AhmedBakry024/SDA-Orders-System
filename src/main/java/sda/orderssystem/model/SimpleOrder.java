package sda.orderssystem.model;

import sda.orderssystem.repository.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class SimpleOrder extends Order {

    public UsersDatabase usersDatabase = UsersDatabase.getInstance();
    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();

    public SimpleOrder(Order order) throws ParseException {
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
