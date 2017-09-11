package com.jira.example.ui.view.sprint;

import com.jira.example.network.response.ItemResponse;
import com.jira.example.ui.BaseView;

/**
 * Created by admin on 9/9/17.
 */

public interface ActiveSprintView extends BaseView {
    void showPage(ItemResponse itemResponse, String type);
}
