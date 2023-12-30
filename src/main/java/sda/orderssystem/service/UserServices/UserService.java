package sda.orderssystem.service.UserServices;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import sda.orderssystem.model.User;
import sda.orderssystem.repository.UsersDatabase;

@Service
public class UserService implements IUserService {

    UsersDatabase usersData = UsersDatabase.getInstance();

    @Override
    public boolean login(String Email, String Pass) {
        usersData.activeUser = -1;

        for (int i = 0; i < usersData.usersDatabase.size(); i++) {
            if (Email.equals(usersData.usersDatabase.get(i).getEmail())
                    && Pass.equals(usersData.usersDatabase.get(i).getPassword())) {
                usersData.activeUser = i;
                System.out.println("Login Successful!");
                return true;
            }
        }
        System.out.println("Invalid Email or Password");
        return false;
    }

    @Override
    public boolean emailValidation(String email) {

        for (int i = 0; i < usersData.usersDatabase.size(); i++) {
            if (email.equals(usersData.usersDatabase.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean AddBalance(int id, int balance) {
        User currentUser = usersData.usersDatabase.get(id);
        currentUser.setBalance(currentUser.getBalance() + balance);
        return true;
    }

    public int getBalance(int id) {
        User currentUser = usersData.usersDatabase.get(id);
        return currentUser.getBalance();
    }

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

        User u1 = new User(user.getName(), usersData.usersDatabase.size(), user.getPhone(), user.getEmail(),
                user.getPassword(), user.getAddress());
        usersData.usersDatabase.add(u1);

        return true; // return validated account

    }

    public User[] getAllUsers() {
        try {
            User[] p = new User[usersData.usersDatabase.size()];
            int i = 0;
            for (User u : usersData.usersDatabase) {
                p[i] = u;
                i++;
            }
            return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllPersons as" + e.getMessage());
        }
        return null;
    }

    public User getUserById(int id) {
        try {
            return usersData.usersDatabase.get(id);
        } catch (Exception e) {
            System.out.println("Exception in getPerson as" + e.getMessage());
        }
        return null;
    }
}

