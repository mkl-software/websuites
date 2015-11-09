package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import junit.framework.Test;


/**
 * Specifies single test class with web test to run.
 * <p>Typically you will specify class extending {@link com.mkl.websuites.test.MultiBrowserTestCase},
 * however you can specify any class that extends {@link junit.framework.Test}.</p>
 * </br>
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestClass {


    /**
     * Class with a test to be launched.
     */
    Class<? extends Test> value();
}
