package com.example.springboottutorial.springboot100steps.exceptions;

import java.util.Date;

public class CustomErrorDetails {
    private  String message;
    private Date timestamp;
    private String errorDetails;

    public CustomErrorDetails(String message, Date timestamp, String errorDetails) {
        this.message = message;
        this.timestamp = timestamp;
        this.errorDetails = errorDetails;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
