package sda.orderssystem.repository;

import java.util.ArrayList;
import sda.orderssystem.model.Order;

// this is the database that will be used to store the orders in the system 
// it is implemented as a singleton
public class OrdersDatabase {
    public ArrayList<Order> ordersDatabase = new ArrayList<>();
    private static OrdersDatabase DatabaseInstance;
    
    private OrdersDatabase() {
    }

    public static OrdersDatabase getInstance() {
        if (DatabaseInstance == null) {
            DatabaseInstance = new OrdersDatabase();
        }
        return DatabaseInstance;
    }
}
