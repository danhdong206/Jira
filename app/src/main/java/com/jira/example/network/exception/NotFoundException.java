package com.jira.example.network.exception;

/**
 * Created by admin on 5/31/17.
 */

public class NotFoundException extends Throwable {
    private String message = "404 Not Found.";

    public NotFoundException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
