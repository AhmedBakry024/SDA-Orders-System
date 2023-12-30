package sda.orderssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.orderssystem.model.SimpleOrder;
import sda.orderssystem.service.OrderServices.OrderService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public boolean addOrder(@RequestBody ArrayList<SimpleOrder> orders) throws ParseException
    {
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
    public List<Object> retrieveOrderById(@PathVariable("id") int id)
    {
        return orderService.retrieveOrderById(id).toList();
    }
}
