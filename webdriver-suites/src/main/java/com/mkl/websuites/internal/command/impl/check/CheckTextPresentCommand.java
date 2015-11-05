package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ListAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


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
