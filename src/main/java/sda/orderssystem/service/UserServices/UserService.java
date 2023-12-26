package sda.orderssystem.service.UserServices;

import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import sda.orderssystem.model.User;
import sda.orderssystem.repository.UsersDatabase;

@Service
public class UserService implements IUserService {

    UsersDatabase usersData = UsersDatabase.getInstance();

    @Override
    public boolean login(String Email , String Pass)
    {
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
    public boolean validateEmail(String email) {
        

        for (int i = 0; i < usersData.usersDatabase.size(); i++) {
            if (email.equals(usersData.usersDatabase.get(i).getEmail())) {
                return false;  //return false if the email already exist
            }
        }
        return true;  // return true if it's a unique email
    }


    @Override
    public boolean createAccount(String name, String email, String password, String phoneNum, String address) {

        //check regular expressions to ensure that the name doesn't contain numbers
        if (name.matches(".*\\d.*")) {
            return false;

        }

        //check the validity of the email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
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

        return true; // return validated account

    }
    
    @Override
    public boolean AddBalance(Double balance)
    {
        User currentUser = usersData.usersDatabase.get(usersData.activeUser);
        currentUser.setBalance(currentUser.getBalance() + balance);
        return true;
    }
    
    public boolean createAccount(User user) {

        //check regular expressions to ensure that the name doesn't contain numbers
        if (user.getName().matches(".*\\d.*")) {
            return false;

        }

        //check the validity of the email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.compile(emailRegex).matcher(user.getEmail()).matches() || !validateEmail(user.getEmail())) {
            return false;
        }

        //check the validity of the password (strong, has at least 8 chars , 1 digit, 1 lowercase, 1 uppercase, 1 special char and no whitespace)
        String passwordRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (!Pattern.compile(passwordRegex).matcher(user.getPassword()).matches()) {
            return false;
        }

        //check the validity of the number (the length and the prefix)
        if (!(user.getPhone().startsWith("010") || user.getPhone().startsWith("011") || user.getPhone().startsWith("012")
                || user.getPhone().startsWith("015")) || !(user.getPhone().length() == 11)) {
            return false;
        }

        User u1 = new User(user.getName(), usersData.usersDatabase.size() + 1000, user.getPhone(), user.getEmail(), user.getPassword(), user.getAddress());
        usersData.usersDatabase.add(u1);

        return true; // return validated account

    }

    public User[] getAllPersons() {
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

    public User getPerson(int id) {
        try {
            return usersData.usersDatabase.get(id);
        } catch (Exception e) {
            System.out.println("Exception in getPerson as" + e.getMessage());
        }
        return null;
    }
}
