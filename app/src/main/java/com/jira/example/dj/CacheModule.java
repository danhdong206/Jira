package com.jira.example.dj;

import com.jira.example.cache.CacheStorage;
import com.jira.example.cache.PreferencesCacheStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danhdong on 9/3/15.
 */
@Module(
        complete = false,
        library = true
)
public class CacheModule {
    @Provides
    @Singleton
    CacheStorage provideCacheStorage(PreferencesCacheStorage preferencesCacheStorage) {
        return preferencesCacheStorage;
    }
}
