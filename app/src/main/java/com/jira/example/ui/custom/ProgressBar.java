package com.jira.example.ui.custom;

import android.content.Context;
import android.util.AttributeSet;

import com.jira.example.R;


/**
 * Created by admin on 5/20/17.
 */

public class ProgressBar extends android.widget.ProgressBar{
    public ProgressBar(Context context) {
        super(context);
        init();
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }
}
