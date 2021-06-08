package com.example.springboottutorial.springboot100steps.controllers;

import com.example.springboottutorial.springboot100steps.entities.User;
import com.example.springboottutorial.springboot100steps.exceptions.UserExistsException;
import com.example.springboottutorial.springboot100steps.exceptions.UserNotFoundException;
import com.example.springboottutorial.springboot100steps.services.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

    @Autowired
    private UserService userService;

    //getUserById -- fields with hashSet
    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1)Long id)
    {
        try{
            Optional<User> userOptional = userService.getUserById(id);
            User user = userOptional.get();

            //Create MappingJackson Value

            //Initializing it as Static Hashset
            Set<String> fields = new HashSet<String>();

            //Whatever added in add will be displayed in the output JSON
            fields.add("id");
            fields.add("username");
            fields.add("ssn");

            //First id parameter should be the same name used in Entity
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            //Define what fields need to be filtered and what needs to be passed

            mapper.setFilters(filterProvider);

            return mapper;

        }
        catch(UserNotFoundException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }

    }

    //getUserById -- fields with params
    @GetMapping("params/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1)Long id, @RequestParam Set<String> fields)
    {
        try{
            Optional<User> userOptional = userService.getUserById(id);
            User user = userOptional.get();

            //Create MappingJackson Value
            //First id parameter should be the same name used in Entity
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            //Define what fields need to be filtered and what needs to be passed

            mapper.setFilters(filterProvider);

            return mapper;

        }
        catch(UserNotFoundException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }

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
}
