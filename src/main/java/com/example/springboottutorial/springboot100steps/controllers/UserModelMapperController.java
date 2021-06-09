package com.example.springboottutorial.springboot100steps.controllers;

import com.example.springboottutorial.springboot100steps.dtos.UserMmDto;
import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.exceptions.UserNotFoundException;
import com.example.springboottutorial.springboot100steps.services.UserService;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

    @Autowired
    private UserService userService;

    //Map model Mapper here
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserMmDto getUserById(@PathVariable("id") @Min(1)Long id) throws UserNotFoundException
    {

        Optional<User> userOptional =  userService.getUserById(id);
        System.out.println("User " + userOptional);
        if(!userOptional.isPresent())
        {
            throw new UserNotFoundException("User Not Found");
        }

        User user = userOptional.get();
        //Here source -> user and destination -> UserMmDto
        UserMmDto userMmDto = modelMapper.map(user,UserMmDto.class);
        return userMmDto;



    }
}
