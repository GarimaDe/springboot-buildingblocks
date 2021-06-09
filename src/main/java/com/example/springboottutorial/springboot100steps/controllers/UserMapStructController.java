package com.example.springboottutorial.springboot100steps.controllers;


import com.example.springboottutorial.springboot100steps.dtos.UserMsDto;
import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.mappers.UserMapper;
import com.example.springboottutorial.springboot100steps.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserMsDto> getAllUserDtos()
    {
        return userMapper.usersToUserDtos(userRepository.findAll());
    }
    @GetMapping("/{id}")
    public  UserMsDto getUserById(@PathVariable("id") Long id)
    {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return userMapper.userToUserDto(user);
    }
}
