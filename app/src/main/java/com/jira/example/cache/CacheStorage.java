package com.jira.example.cache;

/**
 * Created by admin on 10/14/16.
 */

import java.lang.reflect.Type;

public interface CacheStorage {
    void put(String key, Object object);
    void putString(String key, String value);
    <T> T get(String key, Class<T> clazz);
    <T> T get(String key, Type type);
    String getString(String key);
    void clearAll();
}
