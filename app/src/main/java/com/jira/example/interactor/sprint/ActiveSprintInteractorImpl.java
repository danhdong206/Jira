package com.jira.example.interactor.sprint;

import com.jira.example.network.RestService;
import com.jira.example.network.response.ItemResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by admin on 9/11/17.
 */

public class ActiveSprintInteractorImpl implements ActiveSprintInteractor{
    @Inject
    RestService restService;
    @Override
    public Observable<ItemResponse> getActiveSprint(String type) {
        return restService.getActiveSprint(type);
    }
}
