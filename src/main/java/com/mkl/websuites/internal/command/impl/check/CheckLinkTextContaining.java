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

import com.mkl.websuites.command.CommandDescriptor;



@CommandDescriptor(name = "checkLinkTextContaining", argumentTypes = {String.class})
public class CheckLinkTextContaining extends CheckLinkTextCommand {

    public CheckLinkTextContaining(String expectedLinkText) {
        super(expectedLinkText);
    }


    protected String actualLinkText;


    @Override
    protected String getStringParam() {

        try {
            foundElem = browser.findElement(By.partialLinkText(expectedLinkText));
            actualLinkText = foundElem.getText();
        } catch (NoSuchElementException e) {
            return null;
        }
        return "OK";
    }

    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage("Expecting link containing text '%s'" + " to exist", expectedLinkText)
                .isNotNull();
    }

}
