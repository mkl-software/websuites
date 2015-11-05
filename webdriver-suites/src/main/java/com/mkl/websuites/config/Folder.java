package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.internal.tests.SortingStrategy;


@Retention(RetentionPolicy.RUNTIME)
public @interface Folder {

    String path();

    SortingStrategy sortingStrategy() default SortingStrategy.APLHABETICAL;

    boolean ignoreSubfolders() default false;
}
