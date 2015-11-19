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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ObjectArrayAssert;
import org.assertj.core.util.Lists;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;



@CommandDescriptor(name = "checkSelectOptionText", argumentTypes = {String.class, String.class})
public class CheckSelectOptionTextCommand extends OperationOnWebElement {

    protected String selectedTextParam;

    protected String expectedSelectText;

    protected List<WebElement> selectOptions = Lists.emptyList();

    protected List<WebElement> allSelectedOptions;



    public CheckSelectOptionTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckSelectOptionTextCommand(final String selector, final String expectedText) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put("text", expectedText);
            }
        });
        selectedTextParam = "text";
    }

    protected String extracted() {
        return "text";
    }

    protected class CheckSelectOptionText extends AbstractCheck {

        @Override
        protected Object[] getAssertionsParameters() {
            return selectOptions.toArray();
        }

        @Override
        protected AbstractAssert<?, ?> buildAssertion(Object... args) {

            return assertThat(args);
        }

        @Override
        protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {

            ((ObjectArrayAssert<?>) assertion)
                    .extracting("text")
                    .overridingErrorMessage(
                            "Expecting SELECT element picked by selector '%s'"
                                    + " to have text '%s' in one of its options", by, expectedSelectText)
                    .contains(expectedSelectText);
        }
    }



    @Override
    protected void doOperationOnElement(WebElement elem) {

        if (!CommandUtils.checkIfElementIsSelect(elem)) {
            fail("Element expected to be a SELECT");
        }

        Select select = new Select(elem);

        selectOptions = select.getOptions();

        allSelectedOptions = select.getAllSelectedOptions();

        AbstractCheck checkLogic = defineCheckLogic();

        expectedSelectText = parameterMap.get(selectedTextParam);

        checkLogic.runStandardCommand();


    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectOptionText();
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(selectedTextParam);
        }

        return parentValidationRules;
    }

}
