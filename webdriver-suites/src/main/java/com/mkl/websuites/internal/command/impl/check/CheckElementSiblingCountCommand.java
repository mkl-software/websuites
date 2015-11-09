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
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkElementSiblingCount", argumentTypes = {String.class, Integer.class})
public class CheckElementSiblingCountCommand extends CheckElementsCountCommand {



    public CheckElementSiblingCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckElementSiblingCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected class CheckSiblingCount extends AbstractSingleStringCheck {


        @Override
        protected String getStringParam() {
            By directChildren = By.xpath("./../*");
            // find under root element:
            List<WebElement> elements = CheckElementSiblingCountCommand.this.foundElement.findElements(directChildren);
            return (elements.size() - 1) + "";
        }

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {

            assertion.overridingErrorMessage(
                    "Expecting number of siblings of element picked by " + "selector '%s' to be %s but was %s", by,
                    expectedNumberOfElements, actualNumberOfElements).isEqualTo(expectedNumberOfElements);
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckSiblingCount();
    }


}
