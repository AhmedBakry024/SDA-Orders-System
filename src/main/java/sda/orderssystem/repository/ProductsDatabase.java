package sda.orderssystem.repository;

import sda.orderssystem.model.Product;
import sda.orderssystem.model.User;

import java.util.Vector;

public class ProductsDatabase {

    public Vector<Product> productsDatabase = new Vector<>();

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
