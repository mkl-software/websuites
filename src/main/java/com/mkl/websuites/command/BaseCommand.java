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

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.CommonUtils;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.command.SourceInfoHolder;
import com.mkl.websuites.internal.scenario.SourceLine;
import com.mkl.websuites.internal.services.ServiceFactory;


/**
 * Extend this class to write your own commands. It provides useful logic, such as source file
 * information for detailed error messages and browser configuration.
 * 
 * @author Marcin Klosinski
 *
 */
@Slf4j
public abstract class BaseCommand implements Command, SourceInfoHolder {


    protected WebDriver browser;

    private SourceLine sourceLine;

    private static int webElementWaitTimeout = Integer.MIN_VALUE;

    private static SoftAssertions softly = new SoftAssertions();


    @Override
    public void run() {

        if (webElementWaitTimeout == Integer.MIN_VALUE) {
            setWebElementWaitTimeout(WebSuitesConfig.get().site().waitTimeout());
        }

        browser = ServiceFactory.get(BrowserController.class).getWebDriver();

        log.debug("running " + this.getClass().getName() + " command");

        try {
            runStandardCommand();

        } catch (Throwable e) {

            augmentErrorMessageWithCommandSourceFileInfo(e);
            throw e;
        }

    }



    /**
     * Used internally to inject source file information in the command error messages.
     * 
     * @param exception
     */
    protected void augmentErrorMessageWithCommandSourceFileInfo(Throwable exception) {
        try {
            String newMessage = exception.getMessage() + "\n" + getCommandSourceLine().printSourceInfo();
            FieldUtils.writeField(exception, "detailMessage", newMessage, true);
        } catch (IllegalArgumentException | IllegalAccessException | SecurityException e1) {
            e1.printStackTrace();
        }
    }



    /**
     * Implement this method to provide command logic.
     * <p>
     * Standard command means that the command is instantiated using constructor parameters.
     * </p>
     */
    protected abstract void runStandardCommand();



    /**
     * Use this method to clobber properties in the command source (like <code>${property}</code>).
     * 
     */
    protected String populateStringWithProperties(String origValue) {

        return CommonUtils.populateStringWithProperties(origValue);
    }



    /**
     * Used to format detailed messages about where this command come from (scenario file name, line
     * number etc.)
     */
    @Override
    public SourceLine getCommandSourceLine() {
        return sourceLine;
    }


    /**
     * Used internally to inject command source file info.
     */
    @Override
    public void setCommandSourceLine(SourceLine sourceLine) {
        this.sourceLine = sourceLine;
    }



    public static int getWebElementWaitTimeout() {
        return webElementWaitTimeout;
    }



    public static synchronized void setWebElementWaitTimeout(int webElementWaitTimeout) {
        BaseCommand.webElementWaitTimeout = webElementWaitTimeout;
    }



    protected static synchronized SoftAssertions getSoftAssertion() {
        return softly;
    }
    
    protected static synchronized void resetSoftAssertion() {
        softly = new SoftAssertions();
    }
}
