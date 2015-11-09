package com.mkl.websuites.config;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines service implementation for given service class.
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public  @interface Service {

    /**
     * Service class.
     */
    Class<?> service();

    
    /**
     * Implementation for given service class.
     */
    Class<?> implementation();


}