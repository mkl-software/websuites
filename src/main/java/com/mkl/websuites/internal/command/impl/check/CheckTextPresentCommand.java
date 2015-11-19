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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ListAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkText", argumentTypes = String.class)
public class CheckTextPresentCommand extends AbstractCheck {


    protected String text;

    protected WebElement foundElem;

    public CheckTextPresentCommand(String text) {
        this.text = populateStringWithProperties(text);
    }

    @Override
    protected Object[] getAssertionsParameters() {
        List<WebElement> list = browser.findElements(By.xpath("//body//*[contains(text(),'" + text + "')]"));
        if (!list.isEmpty()) {
            foundElem = list.get(0);
        }
        return new Object[] {list};
    }

    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        @SuppressWarnings("unchecked")
        List<WebElement> elementList = (List<WebElement>) args[0];
        return assertThat(elementList);
    }

    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {

        ((ListAssert<?>) assertion).overridingErrorMessage(
                "Text '%s' is not present in any of the elements in the page", text).isNotEmpty();
    }



}
