package com.jira.example.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jira.example.ForActivity;
import com.jira.example.utils.Constants;

import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * Created by admin on 10/14/16.
 */

public class PreferencesCacheStorage implements CacheStorage{
    public static String PREFS = Constants.PREFERENCES_NAME;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Inject
    public PreferencesCacheStorage(@ForActivity Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }
    @Override
    public void put(String key, Object object) {
        if (object != null) {
            Gson gson = new Gson();
            String json = gson.toJson(object, object.getClass());
            mEditor.putString(key, json);
        } else {
            mEditor.putString(key, "");
        }

        mEditor.apply();
    }

    @Override
    public void putString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.apply();
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        String json = mSharedPreferences.getString(key, "");

        if (json.equalsIgnoreCase("")) return null;

        try {
            Gson gson = new Gson();
            T object = gson.fromJson(json, clazz);
            return object;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public <T> T get(String key, Type type) {
        String json = mSharedPreferences.getString(key, "");

        if (json.equalsIgnoreCase("")) return null;

        try {
            Gson gson = new Gson();
            T object = gson.fromJson(json, type);
            return object;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public void clearAll(){
        mEditor.clear().commit();
    }
}
