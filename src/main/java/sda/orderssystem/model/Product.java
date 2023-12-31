package sda.orderssystem.model;

public class Product {
    private int SerialNumber;
    private String ProductName;
    private String Vendor;
    private String Category;
    private int Price;
    private int Quantity;


    // this is a constructor for the Product class
    public Product(int serialNumber, String productName, String vendor, String category, int price, int quantity) {
        SerialNumber = serialNumber;
        ProductName = productName;
        Vendor = vendor;
        Category = category;
        Price = price;
        Quantity = quantity;
    }

    public int getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }
}
