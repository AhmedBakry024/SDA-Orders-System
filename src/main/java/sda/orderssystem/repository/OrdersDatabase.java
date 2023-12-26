package sda.orderssystem.repository;

import java.util.Vector;

import sda.orderssystem.model.User;

public class OrdersDatabase {
    public Vector<User> usersDatabase = new Vector<User>();
    
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
