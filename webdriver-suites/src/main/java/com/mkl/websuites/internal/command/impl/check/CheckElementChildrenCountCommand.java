package com.mkl.websuites.internal.command.impl.check;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkElementChildrenCount", argumentTypes = {String.class, Integer.class})
public class CheckElementChildrenCountCommand extends CheckElementsCountCommand {



    public CheckElementChildrenCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckElementChildrenCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected class CheckChildrenCount extends AbstractSingleStringCheck {


        @Override
        protected String getStringParam() {
            By directChildren = By.xpath("*");
            // find under root element:
            List<WebElement> elements = CheckElementChildrenCountCommand.this.foundElement.findElements(directChildren);
            return elements.size() + "";
        }

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {

            assertion.overridingErrorMessage(
                    "Expecting number of direct children of element picked by " + "selector '%s' to be %s but was %s",
                    by, expectedNumberOfElements, actualNumberOfElements).isEqualTo(expectedNumberOfElements);
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckChildrenCount();
    }


}
