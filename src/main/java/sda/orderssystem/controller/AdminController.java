package sda.orderssystem.controller;

import org.springframework.web.bind.annotation.*;
import sda.orderssystem.service.AdminService;
import sda.orderssystem.service.NotificationService.Message;
import sda.orderssystem.model.Product;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    
    @PostMapping("/add/products")
    public boolean addSampleProducts() {

        adminService.sampleProducts();
        return true;
    }

    @GetMapping("/get/product")
    public ArrayList<Product> getAllProducts() {
        return adminService.retrieveAllProducts();
    }

    @PostMapping("/add/product")
    public boolean addProduct(@RequestBody Product product) {
        return adminService.addProduct(product);
    }
    
    @PutMapping("/ship/{id}")
    public boolean shipOrder(@PathVariable("id") int id) {
        return adminService.shipOrder(id);
    }
    @GetMapping("/notification")
    public ArrayList<Message> getAllNotifications() {
        return adminService.retrieveAllNotifications();
    }
     
}
