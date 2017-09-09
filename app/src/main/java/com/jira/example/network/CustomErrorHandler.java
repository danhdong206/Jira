package com.jira.example.network;


import com.jira.example.network.exception.BadRequest;
import com.jira.example.network.exception.NetworkException;
import com.jira.example.network.exception.NotFoundException;
import com.jira.example.network.exception.UnknownException;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nguyenhoanganh on 9/1/15.
 */
public class CustomErrorHandler implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();

        if (cause.getKind() == RetrofitError.Kind.HTTP) {
            if (r != null) {
                if (r.getStatus() == 400) {
                    return new BadRequest();
                } else if(r.getStatus() == 404){
                    return new NotFoundException();
                }

                return new UnknownException();
            }
        }

        if (cause.getKind() == RetrofitError.Kind.NETWORK) {
            return new NetworkException();
        }
        return cause;
    }
}
