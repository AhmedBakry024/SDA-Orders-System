package sda.orderssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import sda.orderssystem.model.User;
import sda.orderssystem.service.UserServices.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    // All the methods in this class are used to test the functionality of the system
    // if the return type is boolean then it will return true if the operation was successful
    // if the return type is an array then it will return the array of objects

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

    @PutMapping("/prefrence/{id}/{prefrence}")
    public boolean changePrefrence(@PathVariable("id") int id, @PathVariable("prefrence") int prefrence) {

        return userService.changePrefrence(id, prefrence);
    }
}
