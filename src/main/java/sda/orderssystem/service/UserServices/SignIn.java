package sda.orderssystem.service.UserServices;

import sda.orderssystem.repository.UsersDatabase;

import java.util.Scanner;

public class SignIn {
    public boolean login(String Email , String Pass)
    {
        UsersDatabase userDB = UsersDatabase.getInstance();
        userDB.activeUser = -1;


        for(int i=0 ; i<userDB.usersDatabase.size() ; i++)
        {
            if(Email.equals(userDB.usersDatabase.get(i).getEmail())
                    && Pass.equals(userDB.usersDatabase.get(i).getPassword()))
            {
                userDB.activeUser = i;
                System.out.println("Login Successful!");
                return true;
            }
        }
        System.out.println("Invalid Email or Password");
        return false;
    }
}
