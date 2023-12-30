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
        productsDatabase.productsDatabase.add(new Product(1, "Five Feet Apart", "Diwan", "Books", 100, 100));
        productsDatabase.productsDatabase.add(new Product(2, "Lipgloss", "Amanda", "Beauty", 200, 50));
        productsDatabase.productsDatabase.add(new Product(3, "T-Shirt", "LCWaikiki", "Clothes", 300, 100));
        productsDatabase.productsDatabase.add(new Product(4, "Cereal", "GroceryMart", "Grocery", 150, 50));
        productsDatabase.productsDatabase.add(new Product(5, "Laptop", "TechShop", "Electronic devices", 800, 20));
        productsDatabase.productsDatabase.add(new Product(6, "Action Figure", "ToyHaven", "Toys", 50, 200));
        productsDatabase.productsDatabase.add(new Product(7, "Cookware Set", "HomeGoods", "Home and Kitchen", 400, 80));
        productsDatabase.productsDatabase.add(new Product(8, "Notebook", "Shakhabeet", "School Supplies", 30, 150));
        productsDatabase.productsDatabase.add(new Product(9, "Basketball", "Active", "Sports Equipments", 80, 30));

    }

    public ArrayList<Product> retrieveAllProducts() {
        return productsDatabase.productsDatabase;
    }   
}
