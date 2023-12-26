package sda.orderssystem.service.UserServices;

import sda.orderssystem.repository.UsersDatabase;
import sda.orderssystem.model.User;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Signup {

    public boolean validateEmail(String email) {
        UsersDatabase usersData = UsersDatabase.getInstance();

        for (int i = 0; i < usersData.usersDatabase.size(); i++) {
            if (email.equals(usersData.usersDatabase.get(i).getEmail())) {
                return false;  //return false if the email already exist
            }
        }
        return true;  // return true if it's a unique email
    }


    public boolean createAccount(String name, String email, String password, String phoneNum, String address ) {
        UsersDatabase usersData = UsersDatabase.getInstance();

        //check regular expressions to ensure that the name doesn't contain numbers
         if (name.matches(".*\\d.*")) {
            return false;

        }

        //check the validity of the email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.compile(emailRegex).matcher(email).matches() || !validateEmail(email)) {
            return false;
        }

        //check the validity of the password (strong, has at least 8 chars , 1 digit, 1 lowercase, 1 uppercase, 1 special char and no whitespace)
        String passwordRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (!Pattern.compile(passwordRegex).matcher(password).matches()) {
            return false;
        }

        //check the validity of the number (the length and the prefix)
        if (!(phoneNum.startsWith("010") || phoneNum.startsWith("011") || phoneNum.startsWith("012")
                || phoneNum.startsWith("015")) || !(phoneNum.length() == 11)) {
            return false;
        }

        User u1 = new User(name, usersData.usersDatabase.size() + 1000, phoneNum, email, password, address);
        usersData.usersDatabase.add(u1);

        return true;  // return validated account

    }
}
