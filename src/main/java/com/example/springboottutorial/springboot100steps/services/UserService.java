package com.example.springboottutorial.springboot100steps.services;

import com.example.springboottutorial.springboot100steps.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboottutorial.springboot100steps.entities.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //Autowire User repository

    @Autowired
    private UserRepository userRepository;

    //getAllUsers
    public List<User> getAllUsers(){

        return  userRepository.findAll();

    }

    //Create User
    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    //Get user by Id
    public Optional <User>  getUserById(Long id)
    {
    Optional <User> user = userRepository.findById(id);
    return user;
    }
    public User updateUserById(Long id, User user)
    {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id)
    {
        if(userRepository.findById(id).isPresent())
        {
        userRepository.deleteById(id);
        }
    }

    public User getUserByUserName(String username)
    {
        return userRepository.findByUsername(username);
    }
}
