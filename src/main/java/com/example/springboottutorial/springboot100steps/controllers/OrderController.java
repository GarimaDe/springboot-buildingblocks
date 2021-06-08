package com.example.springboottutorial.springboot100steps.controllers;

import com.example.springboottutorial.springboot100steps.entities.Order;
import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.exceptions.UserNotFoundException;
import com.example.springboottutorial.springboot100steps.repositories.OrderRepository;
import com.example.springboottutorial.springboot100steps.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    //Get all orders for user
    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException
    {
        Optional<User> user = userRepository.findById(userid);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("User Not Found!");
        }
        return user.get().getOrders();
    }
    //Create Order
    @PostMapping("/{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException
    {
        Optional<User> user= userRepository.findById(userid);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("User Not Found");
        }
        User userSave = user.get();
        order.setUser(userSave);
        return orderRepository.save(order);

    }
    @GetMapping("/{userid}/orders/{orderid}")
    public Optional<Order> getOrderByOrderId(@PathVariable Long userid , @PathVariable Long orderid) throws UserNotFoundException
    {
        Optional<User> user= userRepository.findById(userid);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("User Not Found");
        }
        Optional<Order> order = orderRepository.findById(orderid);
        if(!order.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order Id is not present");

        }
        return order;
    }
}
