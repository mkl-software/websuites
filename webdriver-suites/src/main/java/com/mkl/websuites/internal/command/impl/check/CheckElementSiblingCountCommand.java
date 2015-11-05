package com.mkl.websuites.internal.command.impl.check;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


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
