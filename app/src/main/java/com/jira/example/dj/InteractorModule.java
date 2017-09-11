package com.jira.example.dj;

import com.jira.example.interactor.sprint.ActiveSprintInteractor;
import com.jira.example.interactor.sprint.ActiveSprintInteractorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 10/14/16.
 */
@Module(
        library = true,
        complete = false,
        includes = {NetworkModule.class, CacheModule.class}
)
public class InteractorModule {
    @Provides
    @Singleton
    ActiveSprintInteractor provideActiveSprintInteractor(ActiveSprintInteractorImpl activeSprintInteractor) {
        return activeSprintInteractor;
    }

}
