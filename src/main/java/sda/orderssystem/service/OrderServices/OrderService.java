package sda.orderssystem.service.OrderServices;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import sda.orderssystem.model.CompoundOrder;
import sda.orderssystem.model.Order;
import sda.orderssystem.model.SimpleOrder;
import sda.orderssystem.repository.OrdersDatabase;
import java.util.ArrayList;

@Service
public class OrderService {

    public OrdersDatabase ordersDatabase = OrdersDatabase.getInstance();

    public boolean addOrder(ArrayList<SimpleOrder> orders) {
        System.out.println("HI");
        if (orders.size() == 1) {
            Order order = new SimpleOrder(orders.get(0));
            System.out.println(order.toString());
            ordersDatabase.ordersDatabase.add(order);
        } else if (orders.size() > 1) {
            Order order = new CompoundOrder(orders);
            for (Order child : order.getChildren()) {
                ordersDatabase.ordersDatabase.add(child);
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
                json.put("customerID", order.getCustomer());
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
                    json.put("customerID", child.getCustomer());
                    json.put("products", child.getProducts().toString());
                    orders.put(json);
                }
            } else {
                System.out.println(order.getId());
                JSONObject json = new JSONObject();
                json.put("id", order.getId());
                json.put("status", order.getStatus());
                json.put("customerID", order.getCustomer());
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


