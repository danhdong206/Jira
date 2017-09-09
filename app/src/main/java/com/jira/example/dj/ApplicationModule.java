package com.jira.example.dj;

/**
 * Created by admin on 9/8/17.
 */


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import android.content.Context;

import com.jira.example.ForActivity;
import com.jira.example.ForApplication;
import com.jira.example.JiraApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {JiraApplication.class},
        includes = CacheModule.class,
        library = true)
public class ApplicationModule {
    private final JiraApplication application;

    public ApplicationModule(JiraApplication application) {
        this.application = application;
    }

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link ForApplication @ForApplication} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    @ForActivity
    Context provideContext() {
        return application;
    }

}