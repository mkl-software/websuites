package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Defines a single scenario file to be launched.
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ScenarioFile {


    /**
     * Path to the scenario file.
     */
    String value();
}
