package com.jira.example;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.jira.example.dj.ApplicationModule;

import dagger.ObjectGraph;

/**
 * Created by admin on 9/8/17.
 */

public class JiraApplication extends MultiDexApplication {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        init();
    }

    private void init(){
        objectGraph = ObjectGraph.create(new ApplicationModule(this));
        objectGraph.inject(this);
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public ObjectGraph getApplicationGraph() {
        return objectGraph;
    }
}
