package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Specifies a browser configuration which will define a browser accessible for tests.
 * <p>The browser can be then simply referred using its local ID, like "ff", "chrome" etc.</p>
 * <p>Sample browser definition:</p>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;browserConfiguration = <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @BrowserConfig}(<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;id = "chrome"<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;displayName = "Chrome"<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;browserType = BrowserType.CHROME<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;webDriverPath = "src/test/resources/ChromeDriver.exe)<br/>
 * </code>
 * <p>You can use the IDs to identify browsers in:</p>
 * <ul>
 *      <li>scenario files in "if statement, e.g.:<code>if  browser=ie</code></li>
 *      <li>scenario files using <code>${browser}</code> property</li>
 *      <li>in Java test clases using <code>WebSuiteProperties.get().getProperty("browser")</code></li>
 * </ul>
 * <p>You can define more than one browser for a given type, for example if you want to use different
 * FireFox profiles. Just specify unique IDs for each browser and the same <code>BrowserType</code>.</p>
 * 
 *  @author Marcin Klosinski
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BrowserConifg {

    public enum BrowserType {

        INTERNET_EXPLORER, CHROME, FIREFOX, SAFARI, OPERA, HTML, NONE
    }

    BrowserType browserType();

    String id();

    String displayName();

    String webDriverPath() default "";
}
