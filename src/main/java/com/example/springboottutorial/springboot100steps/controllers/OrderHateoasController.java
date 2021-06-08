package com.example.springboottutorial.springboot100steps.controllers;

import com.example.springboottutorial.springboot100steps.entities.Order;
import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.exceptions.UserNotFoundException;
import com.example.springboottutorial.springboot100steps.repositories.OrderRepository;
import com.example.springboottutorial.springboot100steps.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    //Get all orders
    //Get all orders for user
    @GetMapping("/{userid}/orders")
    public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException
    {
        Optional<User> user = userRepository.findById(userid);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("User Not Found!");
        }
        List<Order> allorders=  user.get().getOrders();
        CollectionModel<Order> finalResources = new CollectionModel<Order>(allorders);
        return finalResources;
    }
}
