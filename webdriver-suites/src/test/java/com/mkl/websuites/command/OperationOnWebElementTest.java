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


import java.util.HashMap;
import java.util.Map;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.mkl.websuites.command.ParameterizedCommand;


public class OperationOnWebElementTest {


    private OperationOnWebElement sut;


    @Before
    public void init() {
        new MockUp<ParameterizedCommand>() {
            @Mock
            void populateBrowser() {}
        };
    }

    @Test
    public void shouldRunForCss(@Mocked final WebDriver browser) {
        runFor(browser, "css", ".something", By.cssSelector(".something"));
    }


    @Test
    public void shouldRunForId(@Mocked final WebDriver browser) {
        runFor(browser, "id", "someId", By.id("someId"));
    }


    @Test
    public void shouldRunForXpath(@Mocked final WebDriver browser) {
        runFor(browser, "xpath", "//node", By.xpath("//node"));
    }


    @Test
    public void shouldRunForClassName(@Mocked final WebDriver browser) {
        runFor(browser, "className", "someClass", By.className("someClass"));
    }


    @Test
    public void shouldRunForName(@Mocked final WebDriver browser) {
        runFor(browser, "name", "someName", By.name("someName"));
    }


    @Test
    public void shouldRunForTagName(@Mocked final WebDriver browser) {
        runFor(browser, "tagName", "someTagName", By.tagName("someTagName"));
    }


    @Test
    public void shouldRunForLinkText(@Mocked final WebDriver browser) {
        runFor(browser, "linkText", "someLinkText", By.linkText("someLinkText"));
    }


    @Test
    public void shouldRunForPartialLinkText(@Mocked final WebDriver browser) {
        runFor(browser, "partialLinkText", "someParialLinkText", By.partialLinkText("someParialLinkText"));
    }



    private void runFor(final WebDriver browser, String selector, String selectorValue, final By webDriverSelector) {
        // given
        Map<String, String> params = new HashMap<String, String>();
        params.put(selector, selectorValue);
        sut = new OperationOnWebElement(params) {
            @Override
            protected void doOperationOnElement(WebElement elem) {}
        };
        // and
        Deencapsulation.setField(sut, "browser", browser);
        // when
        sut.run();
        // then
        new Verifications() {
            {
                browser.findElement(webDriverSelector);
            }
        };
    }
}
