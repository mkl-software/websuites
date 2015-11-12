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
package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.internal.tests.SortingStrategy;


/**
 * Defines a folder test suite. All tests within the folder structure will be lauched and rendered
 * in a JUnit test tree reflecting folder structure. </p>To run tests just from given folder path
 * without nested folder, please specify <code>ignoreSubfolders=true</code></p>
 * <p>
 * To specify order of processing scenario files and folder you can use different
 * <code>sortinStrategy</code>. However, as of 0.9.x version, only
 * <code>SortingStrategy.APLHABETICAL</code> is supported.
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
     * If set to <code>true</code>, the nested folders will ignored while scanning for scenario
     * files.
     * 
     * @return
     */
    boolean ignoreSubfolders() default false;
}
