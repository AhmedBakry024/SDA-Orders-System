package sda.orderssystem.service.OrderServices;


import org.springframework.stereotype.Service;
import sda.orderssystem.model.CompoundOrder;
import sda.orderssystem.model.Order;
import sda.orderssystem.model.SimpleOrder;

@Service
public class OrderService {

    public boolean addOrder(Order[] orders){
        if(orders.length == 1){
            Order order = new SimpleOrder(orders[0]);
        }
        else if(orders.length > 1){
            Order order = new CompoundOrder(orders);
        }
        return false;
    }
}
