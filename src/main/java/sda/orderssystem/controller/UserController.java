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
import org.springframework.web.bind.annotation.RequestParam;





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
        return userService.getAllPersons();
    }
    
    @GetMapping("/get/{id}")
    public User getPerson(@PathVariable("id") int id) {
        return userService.getPerson(id);
    }
    
    @GetMapping("/get/check/{email}/{password}")
    public boolean login(@PathVariable("email") String email, @PathVariable("password") String password) {

        return userService.login(email, password);
    }

    @PutMapping("/balance/id/{balance}")
    public boolean AddBalance(@PathVariable Double balance) {

        return userService.AddBalance(balance);
    }
    
    @GetMapping("/balance/{id}")
    public Double getMethodName(@PathVariable("id") int id) {
        return userService.getBalance(id);
    }
    

}
