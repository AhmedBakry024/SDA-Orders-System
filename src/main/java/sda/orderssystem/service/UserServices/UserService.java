package sda.orderssystem.service.UserServices;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import sda.orderssystem.model.User;
import sda.orderssystem.repository.UsersDatabase;


/**
 * This class is used to implement the user services
 * it contains the functions that are needed to implement the user services
 */
@Service
public class UserService implements IUserService {

    UsersDatabase usersData = UsersDatabase.getInstance();

    // this function is used to login to the system
    @Override
    public boolean login(String Email, String Pass) {
        usersData.activeUser = -1;

        for (int i = 0; i < usersData.users.size(); i++) {
            if (Email.equals(usersData.users.get(i).getEmail())
                    && Pass.equals(usersData.users.get(i).getPassword())) {
                usersData.activeUser = i;
                System.out.println("Login Successful!");
                return true;
            }
        }
        System.out.println("Invalid Email or Password");
        return false;
    }

    // this function is used to check if the email is already in the system
    @Override
    public boolean emailValidation(String email) {

        for (int i = 0; i < usersData.users.size(); i++) {
            if (email.equals(usersData.users.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    // this function is used to add balance to the user's account
    @Override
    public boolean AddBalance(int id, int balance) {
        User currentUser = usersData.users.get(id);
        currentUser.setBalance(currentUser.getBalance() + balance);
        return true;
    }

    // this function is used to get the balance of the user's account
    public int getBalance(int id) {
        User currentUser = usersData.users.get(id);
        return currentUser.getBalance();
    }

    // this function is used to create a new account
    // it takes a user object as a parameter and returns true if the account was created successfully
    // it returns false if the account wasn't created successfully
    public boolean createAccount(User user) {
        // check regular expressions to ensure that the name doesn't contain numbers
        if (user.getName().matches(".*\\d.*")) {
            return false;
        }

        // check the validity of the email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.compile(emailRegex).matcher(user.getEmail()).matches() || emailValidation(user.getEmail())) {
            return false;
        }

        // check the validity of the password (strong, has at least 8 chars , 1 digit, 1
        // lowercase, 1 uppercase, 1 special char and no whitespace)
        String passwordRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (!Pattern.compile(passwordRegex).matcher(user.getPassword()).matches()) {
            return false;
        }

        // check the validity of the number (the length and the prefix)
        if (!(user.getPhone().startsWith("010") || user.getPhone().startsWith("011")
                || user.getPhone().startsWith("012")
                || user.getPhone().startsWith("015")) || !(user.getPhone().length() == 11)) {
            return false;
        }

        User u1 = new User(user.getName(), usersData.users.size(), user.getPhone(), user.getEmail(),
                user.getPassword(), user.getAddress());
        usersData.users.add(u1);

        return true; // return validated account

    }

    // this function is used to get all the users in the system
    // it returns an array of users
    public User[] getAllUsers() {
        try {
            User[] p = new User[usersData.users.size()];
            int i = 0;
            for (User u : usersData.users) {
                p[i] = u;
                i++;
            }
            return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllPersons as" + e.getMessage());
        }
        return null;
    }

    // this function is used to get a user by his id
    // it takes the id of the user as a parameter and returns the user object
    // it returns null if the user wasn't found
    public User getUserById(int id) {
        try {
            return usersData.users.get(id);
        } catch (Exception e) {
            System.out.println("Exception in getPerson as" + e.getMessage());
        }
        return null;
    }

    // this function is used to change the user's message preference
    // it takes the id of the user and the new preference as parameters
    // it returns true if the preference was changed successfully
    // it returns false if the preference wasn't changed successfully
    public boolean changePrefrence(int id, int prefrence) {
        User currentUser = usersData.users.get(id);
        if(prefrence != 1 && prefrence != 2 && prefrence != 3)
            return false;
        currentUser.setMessagePrefrence(prefrence);
        return true;
    }
}

