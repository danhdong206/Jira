package com.jira.example.ui;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

/**
 * Created by admin on 9/9/17.
 */

public abstract class InjectableFragment extends JiraFragment{
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // expand the activity graph with the fragment-specific module(s)
        ObjectGraph appGraph = getInjectableActivity().getActivityGraph();

        if (appGraph == null) {
            return;
        }

        List<Object> fragmentModules = getModules();
        if (fragmentModules != null && fragmentModules.size() > 0) {
            appGraph = appGraph.plus(fragmentModules.toArray());
        }

        appGraph.inject(this);

        initData();
        sync(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public abstract List<Object> getModules();


    public InjectableActivity getInjectableActivity() {
        return (InjectableActivity) getActivity();
    }

    @Override
    public void sync(boolean loadedData) {

    }

    @Override
    public void initData() {

    }
}
