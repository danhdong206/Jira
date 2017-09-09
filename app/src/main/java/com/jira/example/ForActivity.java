package com.jira.example;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by danhdong on 9/4/15.
 */
@Qualifier @Retention(RUNTIME)
public @interface ForActivity {
}
