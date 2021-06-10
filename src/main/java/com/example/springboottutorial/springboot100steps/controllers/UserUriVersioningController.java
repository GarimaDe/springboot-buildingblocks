package com.example.springboottutorial.springboot100steps.controllers;

import com.example.springboottutorial.springboot100steps.dtos.UserDtoV1;
import com.example.springboottutorial.springboot100steps.dtos.UserDtoV2;
import com.example.springboottutorial.springboot100steps.dtos.UserMmDto;
import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.exceptions.UserNotFoundException;
import com.example.springboottutorial.springboot100steps.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersioningController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    //URI Based Versioning Version 1
    @GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
    public UserDtoV1 getUserById(@PathVariable("id") @Min(1)Long id) throws UserNotFoundException
    {

        Optional<User> userOptional =  userService.getUserById(id);
        System.out.println("User " + userOptional);
        if(!userOptional.isPresent())
        {
            throw new UserNotFoundException("User Not Found");
        }

        User user = userOptional.get();
        //Here source -> user and destination -> UserMmDto
        UserDtoV1 userDtoV1 = modelMapper.map(user,UserDtoV1.class);
        return userDtoV1;



    }

    //URI Based Versioning Version 2
    @GetMapping("/v2.0/{id}")
    public UserDtoV2 getUserById2(@PathVariable("id") @Min(1)Long id) throws UserNotFoundException
    {

        Optional<User> userOptional =  userService.getUserById(id);
        System.out.println("User " + userOptional);
        if(!userOptional.isPresent())
        {
            throw new UserNotFoundException("User Not Found");
        }

        User user = userOptional.get();
        //Here source -> user and destination -> UserMmDto
        UserDtoV2 userDtoV2 = modelMapper.map(user,UserDtoV2.class);
        return userDtoV2;
    }
}
