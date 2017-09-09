package com.jira.example.ui;

import android.os.Bundle;
import android.text.TextUtils;

import com.jira.example.JiraApplication;
import com.jira.example.cache.CacheStorage;
import com.jira.example.dj.ActivityModule;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by admin on 9/9/17.
 */

public abstract class InjectableActivity extends JiraActivity {
    private ObjectGraph activityGraph;
    @Inject
    CacheStorage cacheStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the activity graph by .plus-ing our modules onto the application graph.
        JiraApplication application = (JiraApplication) getApplication();
        activityGraph = application.getApplicationGraph().plus(getModules().toArray());

        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        activityGraph.inject(this);
    }

    /**
     * A list of modules to use for the individual activity graph. Subclasses can override this
     * method to provide additional modules provided they call and include the modules returned by
     * calling {@code super.getModules()}.
     */
    protected List<ActivityModule> getModules() {
        return Arrays.asList(new ActivityModule(this));
    }

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    public void inject(Object object) {
        activityGraph.inject(object);
    }

    public ObjectGraph getActivityGraph() {
        return activityGraph;
    }
}
