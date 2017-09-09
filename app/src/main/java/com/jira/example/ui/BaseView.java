package com.jira.example.ui;

/**
 * Created by admin on 10/13/16.
 */

public interface BaseView {
    void showProgress(String message);
    void showProgress(int resId);
    void hideProgress();
    void showToast(String message);
    void showToast(int resId);
    void showError(Throwable throwable);
}
