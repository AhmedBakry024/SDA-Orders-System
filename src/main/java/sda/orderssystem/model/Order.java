package sda.orderssystem.model;

import java.util.ArrayList;

abstract public class Order {
    
    private int id;
    private String date;
    private String status;
    private String address;
    private User Customer;
    private ArrayList<Product> products;


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
    public User getCustomer() {
        return Customer;
    }
    public void setCustomer(User customer) {
        Customer = customer;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
    
    abstract public void addOrder(Order order);

    abstract public void removeOrder(Order order);

    abstract public Order getChild(int i);

    

}
