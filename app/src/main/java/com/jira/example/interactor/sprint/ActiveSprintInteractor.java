package com.jira.example.interactor.sprint;

import com.jira.example.network.response.ItemResponse;

import rx.Observable;

/**
 * Created by admin on 9/11/17.
 */

public interface ActiveSprintInteractor {
    Observable<ItemResponse> getActiveSprint(String type);
}
