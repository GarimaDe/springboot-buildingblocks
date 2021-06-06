package com.example.springboottutorial.springboot100steps.controllers;

import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    //Autowire UserService
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return  userService.getAllUsers();

    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id)
    {
        return userService.getUserById(id);
    }
    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user)
    {
        return  userService.updateUserById(id,user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id)
    {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username)
    {
        return userService.getUserByUserName(username);
    }
}