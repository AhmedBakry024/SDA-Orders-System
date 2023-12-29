package sda.orderssystem.model;

import sda.orderssystem.repository.ProductsDatabase;

import java.util.ArrayList;

abstract public class Order {
    
    protected int id;
    protected String date;
    protected String status;
    protected String address;
    protected int CustomerID;
    protected int totalPrice;

    protected ArrayList<Product> products;
    protected ArrayList<Integer> productsID;

    public ArrayList<Integer> getProductsID() {
        return productsID;
    }

    public void setProductsID(ArrayList<Integer> productsID) {
        this.productsID = productsID;
    }


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

    public ArrayList<Product> getProductByID(Order order) {
        ProductsDatabase db = ProductsDatabase.getInstance();
        ArrayList<Product> products = new ArrayList<Product>();
        for (int i : order.getProductsID()) {
            for (Product p : db.productsDatabase) {
                if (i == p.getSerialNumber()) {
                    products.add(p);
                }
            }
        }
        return products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int calculateTotal(ArrayList<Product> products) {
        int total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public Order() {
        products = new ArrayList<>();
        productsID = new ArrayList<Integer>();
    }
    
    abstract public void addOrder(SimpleOrder order);

    abstract public void removeOrder(SimpleOrder order);

    abstract public Order getChild(int i);

    abstract public ArrayList<SimpleOrder> getChildren();
    
}