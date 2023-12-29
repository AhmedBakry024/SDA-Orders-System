package sda.orderssystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sda.orderssystem.service.AdminService;
import sda.orderssystem.model.Product;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    
    @PostMapping("/add/product")
    public boolean postMethodName() {

        adminService.sampleProducts();
        return true;
    }

    @GetMapping("/get/product")
    public ArrayList<Product> getAllProducts() {
        return adminService.retrieveAllProducts();
    }
    
    
}
