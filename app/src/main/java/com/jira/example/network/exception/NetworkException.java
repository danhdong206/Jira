package com.jira.example.network.exception;


/**
 * Created by nguyenhoanganh on 9/4/15.
 */
public class NetworkException extends Throwable {
    private String message = "Unable to connect to the server. Please check network connection.";

    public NetworkException() {

    }

    public NetworkException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    public String getMessageUUID(){
        return message;
    }
}