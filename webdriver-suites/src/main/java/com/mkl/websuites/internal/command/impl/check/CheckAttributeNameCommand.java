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
package com.mkl.websuites.internal.command.impl.check;

import java.util.List;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkAttributeName", argumentTypes = {String.class})
public class CheckAttributeNameCommand extends AbstractSingleStringCheck {


    protected String attribute;
    protected WebElement elementWithAttribute;

    public CheckAttributeNameCommand(String attribute) {
        super();
        this.attribute = attribute;
    }


    @Override
    protected String getStringParam() {

        List<WebElement> pageElements = browser.findElements(By.xpath("//body//*"));

        for (WebElement webElement : pageElements) {

            boolean foundAttribute =
                    (Boolean) ((JavascriptExecutor) browser).executeScript("var items = {};"
                            + "for (var i = 0; i < arguments[0].attributes.length; ++i) {" + "    if ("
                            + getPredicateString() + ") return true;" + "};" + "return false;", webElement, attribute);

            if (foundAttribute) {
                elementWithAttribute = webElement;
                return "OK";
            }
        }
        return null;
    }


    protected String getPredicateString() {
        return "arguments[0].attributes[i].name === arguments[1]";
    }



    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage(
                "The attribute with name '%s' is expected to appear somewhere on the page " + "(in any element)",
                attribute).isEqualTo("OK");
    }



}
