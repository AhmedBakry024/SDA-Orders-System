package sda.orderssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.orderssystem.model.Order;
import sda.orderssystem.service.OrderServices.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/addOrder")
    public boolean addOrder()
    {
        return true;
    }

    @DeleteMapping("/deleteOrder")
    public boolean cancelOrder()
    {
        return true;
    }

    @GetMapping("/get")
    public Order retrieveOrder()
    {
        return null;
    }

    @GetMapping("/get/{id}")
    public Order retrieveOrder(@PathVariable("id") int id)
    {
        return null;
    }






}
