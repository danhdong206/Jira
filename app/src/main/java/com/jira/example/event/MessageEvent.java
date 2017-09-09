package com.jira.example.event;

import android.os.Bundle;
import android.os.Message;

/**
 * Created by DanhDong on 12/5/2015.
 */
public class MessageEvent {
    public static final int REQUEST_SYNC = 0;
    public static final int REQUEST_SEARCH = 1;
    public static final int REQUEST_FILTER = 2;
    public static final int REQUEST_SEARCH_OPIYN = 3;
    public static final int REQUEST_OPIYN_FILTER = 4;
    public static final int REQUEST_REFRESH_LOGIN = 5;
    public static final int REQUEST_BOOKMARK = 6;
    public static final int REQUEST_UPDATE_UNREAD_NOTIFICATION_COUNT = 7;
    public static final int REQUEST_PROFILE_OPIYN_FILTER = 8;
    public static final int REQUEST_UPDATE_OPIYN_CURRENT_USER = 9;
    public static final int REQUEST_OPIYN_EDITOR_DONE = 10;
    public static final int REQUEST_UPDATE_OPIYN = 11;
    public static final int REQUEST_SELECT_CATEGORY = 12;
    public static final int REQUEST_UPDATE_COMMENT_COUNT = 13;
    public static final int REQUEST_HAS_INTERNET = 14;
    public static final int REQUEST_OPIYN_PHOTO_DONE = 15;
    public static final int REQUEST_FOLLOWING = 16;
    public static final int REQUEST_UPDATE_OPIYN_CHAT = 17;
    public static final int REQUEST_INCREASE_FOLLOWING_NUMBER = 18;
    public static final int REQUEST_DECREASE_FOLLOWING_NUMBER = 19;
    public static final int REQUEST_UPDATE_PHOTO = 20;
    public static final int REQUEST_POST_MESSAGE_SUCCESS = 21;

    private int mRequestCode;
    private Message mMessage;

    public MessageEvent(int requestCode, Message message) {
        this.mRequestCode = requestCode;
        this.mMessage = message;
    }


    public int getRequestCode() {
        return mRequestCode;
    }

    public void setRequestCode(int mRequestCode) {
        this.mRequestCode = mRequestCode;
    }

    public Message getMessage() {
        return mMessage;
    }

    public void setMessage(Message mMessage) {
        this.mMessage = mMessage;
    }

}
