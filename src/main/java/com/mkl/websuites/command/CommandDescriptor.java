/**
 * Copyright 2015 MKL Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mkl.websuites.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Annotation that exposes a class to be detected as a command that you can use inside your scenario
 * files.
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDescriptor {

    /**
     * Command name to be used in scenario files.
     * 
     */
    String name();

    /**
     * List of argument types. If specified, then the command has to have a constructor
     * <b>exactly</b> matching this parameter types.
     * 
     */
    @SuppressWarnings("rawtypes")
    Class[] argumentTypes() default {};

}
