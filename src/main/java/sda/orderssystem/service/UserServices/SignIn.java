package sda.orderssystem.service.UserServices;

import sda.orderssystem.repository.UsersDatabase;

import java.util.Scanner;

public class SignIn {
    public void login()
    {
        UsersDatabase userDB = UsersDatabase.getInstance();
        userDB.activeUser = -1;

        System.out.println("Login Section");
        Scanner scanner = new Scanner(System.in);

    }
}
