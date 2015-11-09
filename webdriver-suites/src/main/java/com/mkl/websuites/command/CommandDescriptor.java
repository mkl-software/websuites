package com.mkl.websuites.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Annotation that exposes a class to be detected as a command that you can use inside
 * your scenario files.
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDescriptor {

    /**
     * Command name to be used in scenario files.
     * @return
     */
    String name();

    /**
     * List of argument types. If specified, then the command has to have a constructor
     * <b>exactly</b> matching this parameter types.
     * @return
     */
    @SuppressWarnings("rawtypes")
    Class[] argumentTypes() default {};

}
