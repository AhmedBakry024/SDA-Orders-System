package sda.orderssystem.repository;

import java.util.ArrayList;

import sda.orderssystem.model.User;


public class UsersDatabase {

    public ArrayList<User> usersDatabase = new ArrayList<>();
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
