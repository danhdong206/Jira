package com.jira.example.dj;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 10/14/16.
 */
@Module(
        library = true,
        complete = false,
        includes = {NetworkModule.class, CacheModule.class}
)
public class InteractorModule {

}
