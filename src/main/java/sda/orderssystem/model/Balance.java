package sda.orderssystem.model;
import sda.orderssystem.repository.UsersDatabase;

public class Balance {
    UsersDatabase User=UsersDatabase.getInstance();

    void AddBalance(Double balance)
    {
        User currentUser=User.usersDatabase.get(User.activeUser);
        currentUser.setBalance(currentUser.getBalance()+balance);
    }

}
