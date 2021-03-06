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
package com.mkl.websuites.itests.sampleclient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.MultiBrowserTestCase;
import com.mkl.websuites.internal.command.impl.check.CheckTextPresentCommand;

public class SampleStandaloneTest extends MultiBrowserTestCase {


    @Override
    protected void runWebTest() {
        browser.get("http://google.com");
        WebElement queryBox = browser.findElement(By.id("lst-ib"));
        queryBox.sendKeys("selenium\n");
        new CheckTextPresentCommand("Selenium - Web Browser Automation").run();
    }

    @Override
    protected String getTestName() {
        return this.getClass().getName();
    }



}
