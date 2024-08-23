package com.project.demo.model;

import org.springframework.stereotype.Component;

@Component
public class AppError {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
