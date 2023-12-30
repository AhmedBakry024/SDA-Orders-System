package sda.orderssystem.service.OrderServices;

import org.json.*;
import org.springframework.stereotype.Service;
import sda.orderssystem.model.*;
import sda.orderssystem.repository.*;
import sda.orderssystem.service.NotificationService.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class OrderService {

    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();
    public UsersDatabase usersDatabase = UsersDatabase.getInstance();
    public ProductsDatabase productsDatabase = ProductsDatabase.getInstance();

    public boolean addOrder(ArrayList<SimpleOrder> orders) {
        // Check if the database has the products that the order contains, else return
        // false.
        if (!CheckProducts(orders))
            return false;
        // Check if the user has enough balance to pay for the order, else return
        // false.. This is only for the simple order.
        if (orders.size() == 1) {
            Order order = new SimpleOrder(orders.get(0));
            User currentUser = usersDatabase.users.get(order.getCustomerID());
            if (currentUser.getBalance() >= order.getTotalPrice() + 40) {
                currentUser.setBalance(currentUser.getBalance() - (order.getTotalPrice() + 40));
                updateQuantity(order.getProducts());
                if (currentUser.getMessagePrefrence() == 1) {
                    ChannelFactory channelFactory = new SMSFactory();
                    channelFactory.createNotification(order);
                } else if (currentUser.getMessagePrefrence() == 2) {
                    ChannelFactory channelFactory = new EmailFactory();
                    channelFactory.createNotification(order);
                } else if(currentUser.getMessagePrefrence() == 3) {
                    ChannelFactory channelFactory = new SMSFactory();
                    channelFactory.createNotification(order);
                    ChannelFactory channelFactory2 = new EmailFactory();
                    channelFactory2.createNotification(order);
                }
                ordersDatabase.ordersDatabase.add(order);
            } else
                return false;

            // Check if the user has enough balance to pay for the order, else return false.
            // This is only for the compound order.
        } else if (orders.size() > 1) {
            Order order = new CompoundOrder(orders);
            for (Order child : order.getChildren()) {
                if (!(usersDatabase.users.get(child.getCustomerID()).getBalance() >= child.getTotalPrice()
                        + (40 / orders.size()))) {
                    return false;
                }
            }
            for (Order child : order.getChildren()) {
                User currentUser = usersDatabase.users.get(child.getCustomerID());
                currentUser.setBalance(currentUser.getBalance()
                        - child.getTotalPrice() + (40 / orders.size()));
                updateQuantity(child.getProducts());
                if (currentUser.getMessagePrefrence() == 1) {
                    ChannelFactory channelFactory = new SMSFactory();
                    channelFactory.createNotification(child);
                } else if (currentUser.getMessagePrefrence() == 2) {
                    ChannelFactory channelFactory = new EmailFactory();
                    channelFactory.createNotification(child);
                } else if(currentUser.getMessagePrefrence() == 3){
                    ChannelFactory channelFactory = new SMSFactory();
                    channelFactory.createNotification(child);
                    ChannelFactory channelFactory2 = new EmailFactory();
                    channelFactory2.createNotification(child);
                }
            }
            
            ordersDatabase.ordersDatabase.add(order);
        }

        return true;
    }

    public JSONArray retrieveOrderById(int id) {
        JSONArray array = new JSONArray();
        for (Order order : ordersDatabase.ordersDatabase) {
            if (order.getId() == id) {
                if (order instanceof CompoundOrder) {
                    for (Order child : order.getChildren()) {
                        JSONObject json = new JSONObject();
                        json.put("status", child.getStatus());
                        json.put("id", child.getId());
                        json.put("products", child.getProducts().toArray());
                        json.put("customerID", child.getCustomerID());
                        json.put("totalPrice", child.getTotalPrice());
                        json.put("date", child.getDate());
                        array.put(json);
                    }
                } else {
                    JSONObject json = new JSONObject();
                    json.put("id", order.getId());
                    json.put("status", order.getStatus());
                    json.put("customerID", order.getCustomerID());
                    json.put("products", order.getProducts().toArray());
                    json.put("date", order.getDate());
                    json.put("totalPrice", order.getTotalPrice());
                    array.put(json);
                }
                return array;
            }
        }
        return null;
    }

    public JSONArray retrieveAllOrders() {
        JSONArray orders = new JSONArray();
        for (Order order : ordersDatabase.ordersDatabase) {
            if (order instanceof CompoundOrder) {
                for (Order child : order.getChildren()) {
                    JSONObject json = new JSONObject();
                    json.put("id", child.getId());
                    json.put("status", child.getStatus());
                    json.put("customerID", child.getCustomerID());
                    json.put("products", child.getProducts().toArray());
                    json.put("totalPrice", child.getTotalPrice());
                    json.put("date", child.getDate());

                    orders.put(json);
                }
            } else {
                JSONObject json = new JSONObject();
                json.put("id", order.getId());
                json.put("status", order.getStatus());
                json.put("customerID", order.getCustomerID());
                json.put("products", order.getProducts().toArray());
                json.put("date", order.getDate());
                json.put("totalPrice", order.getTotalPrice());
                orders.put(json);
            }
        }
        return orders;
    }

    public boolean CheckProducts(ArrayList<SimpleOrder> orders) {
        for (SimpleOrder order : orders) {
            for (Product product : order.getProducts()) {
                for (Product product2 : productsDatabase.productsDatabase) {
                    if (product.getSerialNumber() == product2.getSerialNumber()) {
                        if (product2.getQuantity() == 0) {
                            return false;
                        } else
                            break;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public void updateQuantity(ArrayList<Product> products) {
        for (Product product : products) {
            for (Product product2 : productsDatabase.productsDatabase) {
                if (product.getSerialNumber() == product2.getSerialNumber()) {
                    product2.setQuantity((product2.getQuantity() - 1));
                }
            }
        }
    }

    public boolean deleteOrderPlacement(int id) {
        ordersDatabase.ordersDatabase.get(id).setStatus("Placement Canceled");
        return true;
    }

    public boolean deleteOrderShipment(int id) {
        Date date = Calendar.getInstance().getTime();

        for (Order order : ordersDatabase.ordersDatabase) {
            if (order.getId() == id) {
                if (order instanceof CompoundOrder) {
                    for (Order child : order.getChildren()) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(child.getDate());
                        cal.add(Calendar.MINUTE, 2);
                        Date expiryDate = cal.getTime();
                        child.setStatus("Shipment Canceled");
                    }
                    return true;
                } else if (order instanceof SimpleOrder) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(order.getDate());
                    cal.add(Calendar.MINUTE, 2);
                    Date expiryDate = cal.getTime();
                    order.setStatus("Shipment Canceled");
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
