package com.example.springboottutorial.springboot100steps.repositories;

import com.example.springboottutorial.springboot100steps.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
