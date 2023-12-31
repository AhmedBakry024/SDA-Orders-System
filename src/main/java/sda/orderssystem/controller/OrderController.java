package sda.orderssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.orderssystem.model.SimpleOrder;
import sda.orderssystem.service.OrderServices.OrderService;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    // All the methods in this class are used to test the functionality of the system
    // if the return type is boolean then it will return true if the operation was successful
    // if the return type is an array then it will return the array of objects

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public boolean addOrder(@RequestBody ArrayList<SimpleOrder> orders)
    {
        return orderService.addOrder(orders);
    }

    @DeleteMapping("/delete/{id}")
    public boolean cancelOrder(@PathVariable("id") int id)
    {
        return orderService.deleteOrderPlacement(id);
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

    @DeleteMapping("/delete/shipment/{id}")
    public boolean deleteOrderShipment(@PathVariable("id") int id)
    {
        return orderService.deleteOrderShipment(id);
    }
}
