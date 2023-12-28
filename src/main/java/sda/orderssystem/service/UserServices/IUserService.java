package sda.orderssystem.service.UserServices;

public interface IUserService {

    boolean login(String Email, String Pass);

    boolean emailValidation(String email);

    boolean createAccount(String name, String email, String password, String phoneNum, String address);

    boolean AddBalance(Double balance);

}