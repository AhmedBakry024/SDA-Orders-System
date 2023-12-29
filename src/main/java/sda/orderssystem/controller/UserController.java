package sda.orderssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sda.orderssystem.model.User;
import sda.orderssystem.service.UserServices.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {

        return userService.createAccount(user);
    }

    @GetMapping("/get")
    public User[] getAll() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("/get/check/{email}/{password}")
    public boolean login(@PathVariable("email") String email, @PathVariable("password") String password) {

        return userService.login(email, password);
    }

    @PutMapping("/balance/{id}/{balance}")
    public boolean AddBalance(@PathVariable ("balance") int balance, @PathVariable("id") int id) {

        return userService.AddBalance(id ,balance);
    }
    
    @GetMapping("/balance/{id}")
    public int getBalance(@PathVariable("id") int id) {
        return userService.getBalance(id);
    }
    

}
