package com.jira.example.network.exception;

/**
 * Created by HoaLQ on 8/16/16.
 */
public class UnknownException extends Throwable {
    private String message = "Unable to communicate with server. Please logout and login again.";

    public UnknownException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
