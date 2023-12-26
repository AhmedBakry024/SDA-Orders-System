package sda.orderssystem.service.UserServices;
import sda.orderssystem.model.User;
import sda.orderssystem.repository.UsersDatabase;

public class Balance {
    UsersDatabase User=UsersDatabase.getInstance();

    void AddBalance(Double balance)
    {
        sda.orderssystem.model.User currentUser=User.usersDatabase.get(User.activeUser);
        currentUser.setBalance(currentUser.getBalance()+balance);
    }

}
