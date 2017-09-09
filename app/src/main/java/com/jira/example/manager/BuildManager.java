package com.jira.example.manager;

import com.jira.example.BuildConfig;

/**
 * Created by admin on 10/18/16.
 */

public class BuildManager {
    private static final String STAGING_SERVICE_ENDPOINT = "";
    private static final String PRODUCTION_SERVICE_ENDPOINT = "";

    public static String getServiceEndpoint() {
        if(BuildConfig.DEBUG){
            return STAGING_SERVICE_ENDPOINT;
        } else{
            return PRODUCTION_SERVICE_ENDPOINT;
        }
    }
}
