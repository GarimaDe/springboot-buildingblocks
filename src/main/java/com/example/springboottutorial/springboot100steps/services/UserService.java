package com.example.springboottutorial.springboot100steps.services;

import com.example.springboottutorial.springboot100steps.exceptions.UserExistsException;
import com.example.springboottutorial.springboot100steps.exceptions.UserNotFoundException;
import com.example.springboottutorial.springboot100steps.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.springboottutorial.springboot100steps.entities.User;
import org.springframework.web.server.ResponseStatusException;

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
    public User createUser(User user) throws UserExistsException
    {
        //if user exist using username
        User existingUser = userRepository.findByUsername(user.getUsername());
        //if not exists throw UserExistException
        if(existingUser != null)
        {
            throw new UserExistsException("User already exists in repository");
        }

        return userRepository.save(user);
    }

    //Get user by Id
    public Optional <User>  getUserById(Long id) throws UserNotFoundException
    {
    Optional <User> user = userRepository.findById(id);
    if(!user.isPresent())
    {
        throw new UserNotFoundException("User not found in User Repository");
    }
    return user;
    }
    public User updateUserById(Long id, User user) throws UserNotFoundException
    {
        Optional <User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent())
        {
            throw new UserNotFoundException("User not found in User Repository. Provide correct user Id");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id)
    {
        Optional <User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in User Repository. Provide correct user Id");
        }

        userRepository.deleteById(id);
    }

    public User getUserByUserName(String username)
    {
        return userRepository.findByUsername(username);
    }
}
