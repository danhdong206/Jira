package com.jira.example.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jira.example.R;
import com.jira.example.event.MessageEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 10/13/16.
 */

public abstract class JiraFragment extends RxOpiynFragment implements BaseView{
    public ProgressDialog progressDialog;
    AlertDialog dialog;
    View dialogView;
    public abstract void sync(boolean loadData);

    public abstract void initData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void onEvent(MessageEvent event) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(canCloseDialogProgress());
        progressDialog.setCancelable(canCloseDialogProgress());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.view_progress_bar, null);
        builder.setView(dialogView);
        dialog = builder.create();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        Toast.makeText(getContext(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    public void showProgress(final String message) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                progressDialog.setMessage(message);
//                progressDialog.show();
                TextView txtMessage = (TextView) dialogView.findViewById(R.id.txt_message);
                txtMessage.setText(message);
                dialog.show();
            }
        }, 10);
    }

    public void showProgress(final int resId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                progressDialog.setMessage(getString(resId));
//                progressDialog.show();
                TextView txtMessage = (TextView) dialogView.findViewById(R.id.txt_message);
                txtMessage.setText(resId);
                dialog.show();
            }
        }, 10);


    }

    public void hideProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (progressDialog != null && progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
                if(getActivity() == null || getActivity().isFinishing()){
                    return;
                }
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, 10);

    }

    protected boolean canCloseDialogProgress() {
        return true;
    }

    @Override
    public void showError(Throwable throwable){
        updateUI(throwable);
    }

    public abstract void updateUI(Throwable throwable);
}
