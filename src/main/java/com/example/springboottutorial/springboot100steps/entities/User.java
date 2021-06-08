package com.example.springboottutorial.springboot100steps.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private  Long id;

    @Column(name="USER_NAME", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory field. Please provide username")
    private  String username;

    @Column(name="FIRST_NAME", length = 50, nullable = false)
    @Size(min=2, message = "First name should have atleast 2 characters")
    private  String firstName;
    @Column(name="LAST_NAME", length = 50, nullable = false)
    private String lastName;
    @Column(name="EMAIL_ADDRESS", length = 50, nullable = false)
    private String email;
    @Column(name="ROLE", length = 50, nullable = false)
    private  String role;
    @Column(name="SSN", length = 50, nullable = false, unique = true)
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    //No Argument Constructor
    public User() {
    }
    //Fields Constructor
    public User(Long id, String username, String firstName, String lastName, String email, String role, String ssn) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
//ToString


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                ", orders=" + orders +
                '}';
    }
}
