package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Specifies extension configuration for custom framework behaviour.
 * <p>It's used to do define scan path to packages with custom commands definitions,
 * which can be used in your scenario files. For example, if you specify this element
 * like below:</p>
 * <code>
 * extension = {@literal @}Extension(</br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;commandExtensionPackages = "com.mycompany.ext"
 * )
 * </code>
 * <p><b>All</b> classes with a {@link com.mkl.websuites.command.CommandDescriptor} annotation
 * that are located in <b>any</b> package under <code>com.mycompany.ext</code>
 * will be automatically available to be used as commands in your scenario files.<p>
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Extension {

    /**
     * List of packages that will be automatically scanned for custom command definitions.
     * @see com.mkl.websuites.config.Extension
     */
    String[] commandExtensionPackages() default {};

    /**
     * List of custom service implementation for deeper framework customization.
     * </p>Example:</p>
     * <code>
     * serviceOverrides = {@literal @}Service(</br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;service = com.mkl.websuite.internal.browser.BrowserController,<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;implementation = com.mycompany.websuites.ext.MyCustomBrowserController<br/>
     * )
     * </code>
     */
    Service[] serviceOverrides() default {};
}
