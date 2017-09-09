package com.jira.example.network;

import com.jira.example.cache.CacheStorage;

import javax.inject.Inject;

import retrofit.RequestInterceptor;


public class CustomRequestInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_KEY = "Authorization";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String X_APP_LANGUAGE = "X-App-Language";
    private static final String X_APP_VERSION = "X-App-Version";
    private static final String X_APP_OS = "X-App-OS";
    private static final String X_APP_LOCATION = "X-App-Location";
    @Inject
    CacheStorage cacheStorage;

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(X_APP_OS, "android");
    }
}
