package sda.orderssystem.service.UserServices;

public interface IUserService {

    boolean login(String Email, String Pass);

    boolean emailValidation(String email);

    boolean AddBalance(int id,int balance);

}