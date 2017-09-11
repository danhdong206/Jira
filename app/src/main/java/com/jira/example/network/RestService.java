package com.jira.example.network;


import com.jira.example.network.response.ItemResponse;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by admin on 10/18/16.
 */

public interface RestService {
    @GET("/sprint/active/{type}")
    Observable<ItemResponse> getActiveSprint(@Path("type") String type);
}
