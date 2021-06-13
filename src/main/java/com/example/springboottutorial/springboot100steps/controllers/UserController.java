package com.example.springboottutorial.springboot100steps.controllers;

import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.exceptions.UserExistsException;
import com.example.springboottutorial.springboot100steps.exceptions.UserNameNotFoundException;
import com.example.springboottutorial.springboot100steps.exceptions.UserNotFoundException;
import com.example.springboottutorial.springboot100steps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {

    //Autowire UserService
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){

        return  userService.getAllUsers();

    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder)
    {
        try{
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
        }
        catch(UserExistsException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }

    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") @Min(1)Long id)
    {
        try{
            Optional<User> userOptional = userService.getUserById(id);
            return userOptional.get();
        }
        catch(UserNotFoundException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }

    }
    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            return userService.updateUserById(id, user);
        }
        catch(UserNotFoundException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id)
    {
        userService.deleteUserById(id);
    }

    @GetMapping("/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException
    {
        User user= userService.getUserByUserName(username);
        if(user==null)
        {
            throw new UserNameNotFoundException("Username '"+username+"' not found in User repositoory");
        }
        return user;
    }
}
