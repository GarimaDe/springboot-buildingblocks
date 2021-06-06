package com.example.springboottutorial.springboot100steps.repositories;


import com.example.springboottutorial.springboot100steps.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query
    User findByUsername(String username);
}
