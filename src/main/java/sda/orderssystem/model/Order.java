package sda.orderssystem.model;

import sda.orderssystem.repository.ProductsDatabase;

import javax.sound.sampled.Port;
import java.util.ArrayList;

abstract public class Order {
    
    protected int id;
    protected String date;
    protected String status;
    protected String address;
    protected int CustomerID;

    protected ArrayList<Product> products;

    public ArrayList<Integer> getProductsID() {
        return productsID;
    }

    public void setProductsID(ArrayList<Integer> productsID) {
        this.productsID = productsID;
    }

    protected ArrayList<Integer> productsID;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getCustomer() {
        return CustomerID;
    }
    public void setCustomer(int customerID) {
        CustomerID = customerID;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
    
    abstract public void addOrder(Order order);

    abstract public void removeOrder(Order order);

    abstract public Order getChild(int i);

    public ArrayList<Product> getProductByID(Order order){
        ProductsDatabase db = ProductsDatabase.getInstance();
        ArrayList<Product> products = new ArrayList<Product>();
        for(int i: order.getProductsID()){
            for(Product p : db.productsDatabase) {
                if (i == p.getSerialNumber()){
                    products.add(p);
                }
            }
        }
        return products;
    }

    

}
