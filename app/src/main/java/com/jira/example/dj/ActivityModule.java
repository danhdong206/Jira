package com.jira.example.dj;

import android.content.Context;

import com.jira.example.ForActivity;
import com.jira.example.ui.JiraActivity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by nguyenhoanganh on 9/4/15.
 */
@Module(
        injects = {
        },
        addsTo = ApplicationModule.class,
        includes = InteractorModule.class,
        library = true,
        complete = false
)
public class ActivityModule {
    private JiraActivity baseActivity;

    public ActivityModule(JiraActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    /**
     * Allow the activity context to be injected but require that it be annotated with
     * {@link ForActivity @ForActivity} to explicitly differentiate it from application context.
     */
    @Provides
    @Singleton
    @ForActivity
    Context provideActivityContext() {
        return baseActivity;
    }

    @Provides
    @Singleton
    @Named("io-scheduler")
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("ui-scheduler")
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
