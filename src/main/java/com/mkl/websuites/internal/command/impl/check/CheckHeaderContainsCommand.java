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

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkHeaderContains", argumentTypes = {String.class})
public class CheckHeaderContainsCommand extends AbstractSingleStringCheck {


    protected String expectedHeader;


    public CheckHeaderContainsCommand(String expectedHeader) {
        super();
        this.expectedHeader = expectedHeader;
    }


    @Override
    protected String getStringParam() {
        WebElement headElem;
        try {
            headElem = browser.findElement(By.tagName("head"));
            return headElem.getAttribute("innerHTML");
        } catch (NoSuchElementException e) {
            return "";
        }
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatHeader, String header) {

        assertThatHeader.overridingErrorMessage("Expecting web page header to contain '%s', but was '%s'",
                expectedHeader, header).contains(expectedHeader);
    }


}
