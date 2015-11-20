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

/**
 * Specifies extension configuration for custom framework behaviour.
 * <p>
 * It's used to do define scan path to packages with custom commands definitions, which can be used
 * in your scenario files. For example, if you specify this element like below:
 * </p>
 * <pre>
 * extension = {@literal @}Extension(
 *      commandExtensionPackages = "com.mycompany.ext"
 * )
 * </pre>
 * <p>
 * <b>All</b> classes with a {@link com.mkl.websuites.command.CommandDescriptor} annotation that are
 * located in <b>any</b> package under <code>com.mycompany.ext</code> will be automatically
 * available to be used as commands in your scenario files.
 * </p>
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Extension {

    /**
     * List of packages that will be automatically scanned for custom command definitions.
     * 
     * @see com.mkl.websuites.config.Extension
     */
    String[] commandExtensionPackages() default {};

    /**
     * List of custom service implementation for deeper framework customization.
     * <p>Example:</p>
     * <code>
     * serviceOverrides = {@literal @}Service(
     *      service = com.mkl.websuite.internal.browser.BrowserController,
     *      implementation = com.mycompany.websuites.ext.MyCustomBrowserController
     * )
     * </code>
     */
    Service[] serviceOverrides() default {};
}
