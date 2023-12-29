package sda.orderssystem.model;

import sda.orderssystem.repository.OrdersDatabase;
import sda.orderssystem.repository.UsersDatabase;
import java.util.ArrayList;

public class SimpleOrder extends Order {

    public UsersDatabase usersDatabase = UsersDatabase.getInstance();
    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();

    public SimpleOrder(Order order) {
        super();
        this.address = order.getAddress();
        this.products = order.getProductByID(order);
        this.totalPrice = order.calculateTotal(this.products);
        this.CustomerID = usersDatabase.activeUser;
        this.status = "Placed";
        this.id = ordersDatabase.ordersDatabase.size() + 1;
        this.date = java.time.LocalDate.now().toString();
    }

    public SimpleOrder() {
        super();
    }

    @Override
    public Order getChild(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChild'");
    }

    @Override
    public void addOrder(SimpleOrder order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOrder'");
    }

    @Override
    public void removeOrder(SimpleOrder order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOrder'");
    }

    @Override
    public ArrayList<SimpleOrder> getChildren() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChildren'");

    }
    

}
