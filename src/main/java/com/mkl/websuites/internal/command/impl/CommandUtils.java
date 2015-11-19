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
package com.mkl.websuites.internal.command.impl;

import java.util.regex.Pattern;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

public class CommandUtils {

    public static void softFail(SoftAssertions softAssertion, String message) {

        // no "fail(msg)" method in soft assertions :/ Workaround:
        softAssertion.assertThat(false).overridingErrorMessage(message).isTrue();
    }


    public static boolean checkIfElementIsSelect(WebElement element) {
        return element.getTagName().equalsIgnoreCase("select");
    }


    public static Pattern patternOf(String text) {

        return Pattern.compile(text, Pattern.DOTALL);
    }


    public static boolean checkIfElementIsCheckBox(WebElement element) {
        String checkedAtt = element.getAttribute("type");
        return element.getTagName().equalsIgnoreCase("input") && checkedAtt != null && checkedAtt.equals("checkbox");
    }

}
