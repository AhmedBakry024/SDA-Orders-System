package sda.orderssystem.repository;

import sda.orderssystem.model.Product;

import java.util.ArrayList;

public class ProductsDatabase {

    public ArrayList<Product> productsDatabase = new ArrayList<>();

    private static ProductsDatabase DatabaseInstance;

    private ProductsDatabase() {

    }

    public static ProductsDatabase getInstance() {
        if (DatabaseInstance == null) {
            DatabaseInstance = new ProductsDatabase();
        }
        return DatabaseInstance;
    }
}
