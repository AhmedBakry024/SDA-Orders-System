package sda.orderssystem.repository;

import java.util.ArrayList;
import sda.orderssystem.model.User;

// this is the database that will be used to store the users in the system
// it is implemented as a singleton
public class UsersDatabase {

    public ArrayList<User> users = new ArrayList<>();
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
