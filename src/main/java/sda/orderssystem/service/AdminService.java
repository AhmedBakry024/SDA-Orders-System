package sda.orderssystem.service;

import org.springframework.stereotype.Service;

import sda.orderssystem.model.*;
import sda.orderssystem.repository.*;
import sda.orderssystem.service.NotificationService.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This is the AdminService class.
 * It contains the methods that will be used by the admin.
 */
@Service
public class AdminService {

    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();
    public ProductsDatabase productsDatabase = ProductsDatabase.getInstance();
    public NotificationQueue notificationQueue = NotificationQueue.getInstance();
    public UsersDatabase usersDatabase = UsersDatabase.getInstance();
    public TemplateCount templateCount = TemplateCount.getInstance();

    // this function is used to add a new sample products to the system
    // It's recommended to use this function at first to add some sample products to
    // the system
    // so that you can test the functionality of the system
    public void sampleProducts() {
        // add sample products to productsDatabase and sending parameters to Product
        // constructor
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

    // this function is used to retrieve all the products in the system
    public ArrayList<Product> retrieveAllProducts() {
        return productsDatabase.productsDatabase;
    }

    // this function is used to add a new product to the system
    public boolean addProduct(Product product) {
        return productsDatabase.productsDatabase.add(product);
    }

    // this function is used to ship an order
    // it takes the order id as a parameter and returns true if the order was
    // shipped successfully
    // Admin can only ship orders that are placed
    public boolean shipOrder(int orderID) {
        if (ordersDatabase.ordersDatabase.get(orderID) instanceof SimpleOrder
                && ordersDatabase.ordersDatabase.get(orderID).getStatus().equals("Placed")) {
            Order order = ordersDatabase.ordersDatabase.get(orderID);
            ordersDatabase.ordersDatabase.get(orderID).setStatus("Shipped");
            User currentUser = usersDatabase.users.get(order.getCustomerID());
            if (currentUser.getMessagePrefrence() == 1) {
                ChannelFactory channelFactory = new SMSFactory();
                channelFactory.createNotification(order);
            } else if (currentUser.getMessagePrefrence() == 2) {
                ChannelFactory channelFactory = new EmailFactory();
                channelFactory.createNotification(order);
            } else if (currentUser.getMessagePrefrence() == 3) {
                ChannelFactory channelFactory = new SMSFactory();
                channelFactory.createNotification(order);
                ChannelFactory channelFactory2 = new EmailFactory();
                channelFactory2.createNotification(order);
            }
            return true;
        } else if (ordersDatabase.ordersDatabase.get(orderID) instanceof CompoundOrder) {
            for (SimpleOrder child : ordersDatabase.ordersDatabase.get(orderID).getChildren()) {
                child.setStatus("Shipped");
                User currentUser = usersDatabase.users.get(child.getCustomerID());
                if (currentUser.getMessagePrefrence() == 1) {
                    ChannelFactory channelFactory = new SMSFactory();
                    channelFactory.createNotification(child);
                } else if (currentUser.getMessagePrefrence() == 2) {
                    ChannelFactory channelFactory = new EmailFactory();
                    channelFactory.createNotification(child);
                } else if (currentUser.getMessagePrefrence() == 3) {
                    ChannelFactory channelFactory = new SMSFactory();
                    channelFactory.createNotification(child);
                    ChannelFactory channelFactory2 = new EmailFactory();
                    channelFactory2.createNotification(child);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // this function is used to get all the notifications in the system from the
    // notification queue
    public ArrayList<Message> retrieveAllNotifications() {
        return notificationQueue.listAllNotifications();
    }

    public String mostUsedTemplate() {
        // initialize the template count with 0 and 0
        Entry<Integer, Integer> highest = Map.entry(0, 0);
        for (Map.Entry<Integer, Integer> entry : templateCount.getTemplateCount().entrySet()) {
            if (entry.getValue() > highest.getValue()) {
                highest = entry;
            }
        }
        if (highest.getKey() == 1) {
            return "Template 1 is the most used template : \"Dear \" + CustomerName + \", your order \" + OrderId + \" is \" + OrderStatus\r\n" + //
                    " + \". Thank you for shopping with us.\"";
        } else if (highest.getKey() == 2) {
            return "Template 2 is the most used template : \"Dear \" + CustomerName + \", your product \" + Product\r\n" + //
                    "  + \" is confirmed. Thank you for shopping with us.\"";
        } else if (highest.getKey() == 3) {
            return "Template 3 is the most used template : \"Dear \" + CustomerName + \", your product \" + Product\r\n" + //
                    "  + \" is shipped. Thank you for using our store \" + Vendor + \" :)\"";
        } else {
            return "No templates used yet";
        }

    }
}
