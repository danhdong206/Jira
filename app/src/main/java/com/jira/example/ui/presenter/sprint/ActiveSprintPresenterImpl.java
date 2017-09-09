package com.jira.example.ui.presenter.sprint;

import android.content.Context;

import com.jira.example.ForApplication;
import com.jira.example.ui.view.sprint.ActiveSprintView;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;

/**
 * Created by admin on 9/9/17.
 */

public class ActiveSprintPresenterImpl implements ActiveSprintPresenter{
    private ActiveSprintView activeSprintView;
    private Scheduler schedulerIo;
    private Scheduler schedulerUi;
    @Inject
    @ForApplication
    Context context;

    @Inject
    public ActiveSprintPresenterImpl(ActiveSprintView activeSprintView,
                              @Named("io-scheduler") Scheduler schedulerIo,
                              @Named("ui-scheduler") Scheduler schedulerUi) {
        this.activeSprintView = activeSprintView;
        this.schedulerIo = schedulerIo;
        this.schedulerUi = schedulerUi;
    }
}
