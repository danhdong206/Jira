package com.jira.example.ui;

import android.os.Bundle;

import com.jira.example.R;
import com.jira.example.ui.view.sprint.ActiveSprintFragment;

/**
 * Created by admin on 9/9/17.
 */

public class ActiveSprintActivity extends InjectableActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_sprint);
        replaceFragment(R.id.container, ActiveSprintFragment.newInstance(), ActiveSprintFragment.TAG, false);
    }
}
