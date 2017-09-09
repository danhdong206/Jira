package com.jira.example.dj;

import com.jira.example.ui.presenter.sprint.ActiveSprintPresenter;
import com.jira.example.ui.presenter.sprint.ActiveSprintPresenterImpl;
import com.jira.example.ui.view.sprint.ActiveSprintFragment;
import com.jira.example.ui.view.sprint.ActiveSprintView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 9/9/17.
 */
@Module(
        injects = ActiveSprintFragment.class,
        includes = InteractorModule.class,
        library = true,
        complete = false
)
public class ActiveSprintModule {
    private ActiveSprintView activeSprintView;

    public ActiveSprintModule(ActiveSprintView activeSprintView) {
        this.activeSprintView = activeSprintView;
    }

    @Provides
    @Singleton
    ActiveSprintView provideActiveSprintView() {
        return activeSprintView;
    }

    @Provides
    @Singleton
    ActiveSprintPresenter provideActiveSprintPresenter(ActiveSprintPresenterImpl activeSprintPresenter) {
        return activeSprintPresenter;
    }
}
