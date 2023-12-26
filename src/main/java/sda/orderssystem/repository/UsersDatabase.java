package sda.orderssystem.repository;

import java.util.Vector;

import sda.orderssystem.model.User;


public class UsersDatabase {

    public Vector<User> usersDatabase = new Vector<User>();
    
    public int activeUser = -1;
    private static UsersDatabase DatabaseInstance;

    private UsersDatabase() {

    }

    public static UsersDatabase getInstance() {
        if (DatabaseInstance == null) {
            DatabaseInstance = new UsersDatabase();
        }
        return DatabaseInstance;
    }
}
