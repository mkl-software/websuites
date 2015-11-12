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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


/**
 * Convienience class to be extended by commands that directly operate on a web element.
 * <p>
 * It has parameterized configuration for {@link org.openqa.selenium.By} selector
 * </p>
 * <p>
 * The default selector is <code>css</code>.
 * </p>
 * 
 * @author klosinskim
 *
 */
public abstract class OperationOnWebElement extends ParameterizedCommand {


    protected String elementSelector;

    protected By by;

    protected WebElement foundElement;

    public OperationOnWebElement(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public OperationOnWebElement(final String selector) {
        this(new HashMap<String, String>() {
            {
                put("css", selector);
            }
        });
    }

    @Override
    protected void runStandardCommand() {

        parameterMap = new HashMap<String, String>();
        parameterMap.put("css", populateStringWithProperties(elementSelector));
        runCommandWithParameters();
    }


    @Override
    protected void runCommandWithParameters() {


        by = null;

        if (parameterMap.keySet().contains("id")) {
            by = By.id(parameterMap.get("id"));
        }

        if (parameterMap.keySet().contains("css")) {
            by = By.cssSelector(parameterMap.get("css"));
        }

        if (parameterMap.keySet().contains("xpath")) {
            by = By.xpath(parameterMap.get("xpath"));
        }

        if (parameterMap.keySet().contains("className")) {
            by = By.className(parameterMap.get("className"));
        }

        if (parameterMap.keySet().contains("linkText")) {
            by = By.linkText(parameterMap.get("linkText"));
        }

        if (parameterMap.keySet().contains("partialLinkText")) {
            by = By.partialLinkText(parameterMap.get("partialLinkText"));
        }

        if (parameterMap.keySet().contains("name")) {
            by = By.name(parameterMap.get("name"));
        }

        if (parameterMap.keySet().contains("tagName")) {
            by = By.tagName(parameterMap.get("tagName"));
        }


        try {

            WebElement elem = browser.findElement(by);

            foundElement = elem;

            doOperationOnElement(elem);

        } catch (NoSuchElementException e) {

            fail("No element found for selecor " + by + " can be found on the page. " + "Selection parameters: "
                    + parameterMap);
        }
    }


    /**
     * Exposed to override for soft assertions. Hard implementation by default:
     * 
     * @param message
     */
    protected void fail(String message) {
        Assert.fail(message);
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> rules = new ArrayList<SchemaValidationRule>();
        String[] validParams =
                new String[] {"css", "id", "xpath", "linkText", "partialLinkText", "className", "name", "tagName"};

        for (String paramName : validParams) {
            SchemaValidationRule rule = new SchemaValidationRule(paramName);
            rules.add(rule);
        }

        return rules;
    }


    /**
     * Implement this to define the logic operating on the given web element.
     * 
     * @param elem web element
     */
    protected abstract void doOperationOnElement(WebElement elem);

}
