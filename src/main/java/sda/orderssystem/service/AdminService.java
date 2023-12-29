package sda.orderssystem.service;

import org.springframework.stereotype.Service;

import sda.orderssystem.model.Product;
import sda.orderssystem.repository.OrdersDatabase;
import sda.orderssystem.repository.ProductsDatabase;
import sda.orderssystem.repository.UsersDatabase;
import java.util.ArrayList;

@Service
public class AdminService {
    
    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();
    public ProductsDatabase productsDatabase = ProductsDatabase.getInstance();
    public UsersDatabase usersDatabase = UsersDatabase.getInstance();

    public void sampleProducts() {
        // add sample products to productsDatabase and sending parameters to Product constructor
        productsDatabase.productsDatabase.add(new Product(1, "Product 1", "Seller1", "Books", 100.0, 100));
        productsDatabase.productsDatabase.add(new Product(2, "Product 2", "Seller2", "Books", 200.0, 100));
        productsDatabase.productsDatabase.add(new Product(3, "Product 3", "Seller3", "Books", 300.0, 100));
    }

    public ArrayList<Product> retrieveAllProducts() {
        return productsDatabase.productsDatabase;
    }   
}
