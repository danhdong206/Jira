package com.jira.example.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.jira.example.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by admin on 9/8/17.
 */

public abstract class JiraActivity extends RxAppCompatActivity implements BaseView{
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressDialog();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    protected void replaceFragment(final int containerViewId, final Fragment fragment, final String TAG, final boolean addToBackStack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
                fragmentTransaction.replace(containerViewId, fragment, TAG);
                if (addToBackStack) {
                    fragmentTransaction.addToBackStack(TAG);
                }
                fragmentTransaction.commitAllowingStateLoss();
            }
        }, 0);
    }

    @Override
    public void showProgress(final String message) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage(message);
                progressDialog.show();
            }
        }, 20);
    }

    @Override
    public void showProgress(final int resId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage(getString(resId));
                progressDialog.show();
            }
        }, 20);
    }

    @Override
    public void hideProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }, 20);

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable){

    }
}
