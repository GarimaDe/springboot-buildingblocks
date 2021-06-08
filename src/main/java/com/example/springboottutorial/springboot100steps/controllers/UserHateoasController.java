package com.example.springboottutorial.springboot100steps.controllers;

import com.example.springboottutorial.springboot100steps.entities.Order;
import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.exceptions.UserNotFoundException;
import com.example.springboottutorial.springboot100steps.repositories.UserRepository;
import com.example.springboottutorial.springboot100steps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;

@RestController
@RequestMapping(value="/hateoas/users")
@Validated
public class UserHateoasController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    //Get user by Id
    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable("id") @Min(1)Long id)
    {
        try{
            Optional<User> userOptional=  userService.getUserById(id);
            User user = userOptional.get();
            //We need the user id to do self linking
            Long userid= user.getId();
            Link selflink = WebMvcLinkBuilder
            .linkTo(this.getClass()).slash(userid).withSelfRel();
            //Adding the link to the user data
            user.add(selflink);
            System.out.println("SelfLink "+ selflink);

            EntityModel<User> finalEntity = new EntityModel<User>(user);
            return finalEntity;

        }
        catch(UserNotFoundException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }

    }
    //Get all users
    @GetMapping
    public CollectionModel<User> getAllUsers() throws UserNotFoundException {

        List<User> allusers = userService.getAllUsers();
        for(User user : allusers)
        {
            //Self link
            Long userid = user.getId();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
            user.add(selfLink);

            //Relationship link with getAllOrders
            CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
                    .getAllOrders(userid);
            Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
            user.add(ordersLink);


        }
        //Self link for getAllUsers
        Link selfLinkAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        CollectionModel<User> finalEntity = new CollectionModel<User>(allusers,selfLinkAllUsers);
        return finalEntity;
    }

}
