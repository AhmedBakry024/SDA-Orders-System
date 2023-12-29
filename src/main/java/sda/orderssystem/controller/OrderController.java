package sda.orderssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.orderssystem.model.SimpleOrder;
import sda.orderssystem.service.OrderServices.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public boolean addOrder(@RequestBody ArrayList<SimpleOrder> orders)
    {
        System.out.println(orders.get(0).getCustomerID());
        System.out.println(orders.get(0).getAddress());
        System.out.println(orders.get(0).getTotalPrice()); 
        return orderService.addOrder(orders);
    
    }

    @DeleteMapping("/delete")
    public boolean cancelOrder()
    {
        return true;
    }

    @GetMapping("/get")
    public List<Object> retrieveAllOrders()
    {
        return orderService.retrieveAllOrders().toList();
    }

    @GetMapping("/get/{id}")
    public Map<String,Object> retrieveOrderById(@PathVariable("id") int id)
    {
        return orderService.retrieveOrderById(id).toMap();
    }

}
