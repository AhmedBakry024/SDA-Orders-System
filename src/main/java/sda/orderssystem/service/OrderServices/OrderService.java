package sda.orderssystem.service.OrderServices;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import sda.orderssystem.model.CompoundOrder;
import sda.orderssystem.model.Order;
import sda.orderssystem.model.SimpleOrder;
import sda.orderssystem.repository.OrdersDatabase;
import sda.orderssystem.repository.UsersDatabase;

import java.util.ArrayList;

@Service
public class OrderService {

    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();
    public UsersDatabase usersDatabase = UsersDatabase.getInstance();

    public boolean addOrder(ArrayList<SimpleOrder> orders) {
        System.out.println("HI");
        if (orders.size() == 1) {
            Order order = new SimpleOrder(orders.get(0));
            System.out.println(orders.get(0).getCustomerID()); 
            if (usersDatabase.usersDatabase.get(order.getCustomerID()).getBalance() >= order.getTotalPrice() + 40) {
                usersDatabase.usersDatabase.get(order.getCustomerID()).setBalance(
                        usersDatabase.usersDatabase.get(order.getCustomerID()).getBalance() - (order.getTotalPrice() + 40));
                ordersDatabase.ordersDatabase.add(order);
            }
            else return false;
        } else if (orders.size() > 1) {
            Order order = new CompoundOrder(orders);
            for (Order child : order.getChildren()) {
                if (usersDatabase.usersDatabase.get(child.getCustomerID()).getBalance() >= child.getTotalPrice() + 40) {
                    usersDatabase.usersDatabase.get(child.getCustomerID())
                            .setBalance(usersDatabase.usersDatabase.get(child.getCustomerID()).getBalance()
                                    - child.getTotalPrice() + (40 / orders.size()));
                    ordersDatabase.ordersDatabase.add(child);
                }
                else return false;
            }
        }

        return true;
    }

    public JSONObject retrieveOrderById(int id) {
        for (Order order : ordersDatabase.ordersDatabase) {
            if (order.getId() == id) {
                JSONObject json = new JSONObject();
                json.put("id", order.getId());
                json.put("status", order.getStatus());
                json.put("customerID", order.getCustomerID());
                json.put("products", order.getProducts().toArray());
                return json;
            }
        }
        return null;
    }

    public JSONArray retrieveAllOrders() {
        JSONArray orders = new JSONArray();
        for (Order order : ordersDatabase.ordersDatabase) {
            if (order instanceof CompoundOrder) {
                for (Order child : ((CompoundOrder) order).getChildren()) {
                    JSONObject json = new JSONObject();
                    json.put("id", child.getId());
                    json.put("status", child.getStatus());
                    json.put("customerID", child.getCustomerID());
                    json.put("products", child.getProducts().toArray());
                    orders.put(json);
                }
            } else {
                System.out.println(order.getId());
                JSONObject json = new JSONObject();
                json.put("id", order.getId());
                json.put("status", order.getStatus());
                json.put("customerID", order.getCustomerID());
                // json.put("products", order.getProducts().toArray());
                orders.put(json);
            }
        }
        for (Object orderObject : orders) {
            JSONObject order = (JSONObject) orderObject;
            System.out.println(order.toString());
        }
        return orders;

    }
}


