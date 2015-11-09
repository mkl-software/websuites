package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.internal.tests.SortingStrategy;


/**
 * Defines a folder test suite. All tests within the folder structure will be lauched and
 * rendered in a JUnit test tree reflecting folder structure.
 * </p>To run tests just from given folder path without nested folder, please specify
 * <code>ignoreSubfolders=true</code></p>
 * <p>To specify order of processing scenario files and folder you can use different
 * <code>sortinStrategy</code>. However, as of 0.9.x version, only <code>SortingStrategy.APLHABETICAL</code>
 * is supported.
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Folder {

    /**
     * Path to root folder, from which all scenario files in all subfolders will be launched.
     */
    String path();

    /**
     * Specifies the order of processing scenario files. Currently only ALPHABETICAL is supported.
     */
    SortingStrategy sortingStrategy() default SortingStrategy.APLHABETICAL;

    
    /**
     * If set to <code>true</code>, the nested folders will ignored while scanning for scenario files.
     * @return
     */
    boolean ignoreSubfolders() default false;
}
