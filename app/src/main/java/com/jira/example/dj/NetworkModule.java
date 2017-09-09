package com.jira.example.dj;

import com.jira.example.BuildConfig;
import com.jira.example.manager.BuildManager;
import com.jira.example.network.CustomErrorHandler;
import com.jira.example.network.CustomRequestInterceptor;
import com.jira.example.network.RestService;
import com.squareup.okhttp.ConnectionSpec;
import com.squareup.okhttp.TlsVersion;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by admin on 10/14/16.
 */
@Module(
        library = true,
        complete = false,
        includes = CacheModule.class
)
public class NetworkModule {

    @Provides
    @Singleton
    ErrorHandler provideErrorHandler() {
        return new CustomErrorHandler();
    }

    @Provides
    @Singleton
    RequestInterceptor provideRequestInterceptor(CustomRequestInterceptor customRequestInterceptor) {
        return customRequestInterceptor;
    }

    @Provides
    @Singleton
    ConnectionSpec provideConnectionSpec() {
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .build();
        return spec;
    }

    @Provides
    @Singleton
    RestService provideRestService(RestAdapter restAdapter) {
        return restAdapter.create(RestService.class);
    }

    @Provides
    @Singleton
    RestAdapter provideCreateRestAdapter(ErrorHandler errorHandler, RequestInterceptor requestInterceptor) {
        String endpoint = BuildManager.getServiceEndpoint();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setErrorHandler(errorHandler)
                .setRequestInterceptor(requestInterceptor)
                .build();
        if(BuildConfig.DEBUG) {
            restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        }
        return restAdapter;
    }
}
