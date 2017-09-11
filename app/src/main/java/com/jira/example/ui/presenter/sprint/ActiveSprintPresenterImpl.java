package com.jira.example.ui.presenter.sprint;

import android.content.Context;

import com.jira.example.ForApplication;
import com.jira.example.R;
import com.jira.example.interactor.sprint.ActiveSprintInteractor;
import com.jira.example.ui.view.sprint.ActiveSprintFragment;
import com.jira.example.ui.view.sprint.ActiveSprintView;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;

/**
 * Created by admin on 9/9/17.
 */

public class ActiveSprintPresenterImpl implements ActiveSprintPresenter{
    private ActiveSprintView activeSprintView;
    private ActiveSprintInteractor activeSprintInteractor;
    private Scheduler schedulerIo;
    private Scheduler schedulerUi;
    @Inject
    @ForApplication
    Context context;

    @Inject
    public ActiveSprintPresenterImpl(ActiveSprintView activeSprintView,
                                     ActiveSprintInteractor activeSprintInteractor,
                                     @Named("io-scheduler") Scheduler schedulerIo,
                                     @Named("ui-scheduler") Scheduler schedulerUi) {
        this.activeSprintView = activeSprintView;
        this.activeSprintInteractor = activeSprintInteractor;
        this.schedulerIo = schedulerIo;
        this.schedulerUi = schedulerUi;
    }

    @Override
    public void getActiveSprint(String type) {
        activeSprintView.showProgress(context.getString(R.string.loading));
        activeSprintInteractor.getActiveSprint(type).subscribeOn(schedulerIo)
                .observeOn(schedulerUi)
                .compose(((ActiveSprintFragment) activeSprintView).bindToLifecycle())
                .subscribe(itemResponse -> {
                    activeSprintView.hideProgress();
                    activeSprintView.showPage(itemResponse, type);
                }, throwable -> {
                    throwable.printStackTrace();
                    activeSprintView.hideProgress();
                    activeSprintView.showPage(null, type);
                }, () -> {

                });
    }
}
