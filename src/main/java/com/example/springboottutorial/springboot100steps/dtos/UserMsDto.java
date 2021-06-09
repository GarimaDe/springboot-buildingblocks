package com.example.springboottutorial.springboot100steps.dtos;

public class UserMsDto {

    private Long id;
    private String username;
    private String emailaddress;

    public UserMsDto() {
    }

    public UserMsDto(Long id, String username, String emailaddress) {
        this.id = id;
        this.username = username;
        this.emailaddress = emailaddress;
    }

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

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
}
