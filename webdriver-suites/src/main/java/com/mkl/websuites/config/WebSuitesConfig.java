package com.mkl.websuites.config;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;


/**
 * Global configuration access. Use <code>WebSuitesConfig.get()</code> to access system configuration.
 * 
 * @author Marcin Klosinski
 *
 */
@Slf4j
public class WebSuitesConfig {

    private static WebSuites config;

    public static void initializeWebsuitesConfig(Class<? extends WebSuitesRunner> runningClass) {
        config = runningClass.getAnnotation(WebSuites.class);
        log.debug("config class: " + config);

    }

    /**
     * Retrieves global configuration.
     * @return  {@link com.mkl.websuites.WebSuites} root configuration.
     */
    public static WebSuites get() {
        return config;
    }
}
