package com.jira.example.network.exception;

/**
 * Created by nguyenhoanganh on 9/22/15.
 */
public class BadRequest extends Throwable {
    private String message = "Bad request";

    @Override
    public String getMessage() {
        return message;
    }
}
