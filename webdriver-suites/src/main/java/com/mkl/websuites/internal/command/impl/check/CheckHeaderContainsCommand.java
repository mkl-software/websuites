package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkHeaderContains", argumentTypes = {String.class})
public class CheckHeaderContainsCommand extends AbstractSingleStringCheck {


    protected String expectedHeader;


    public CheckHeaderContainsCommand(String expectedHeader) {
        super();
        this.expectedHeader = expectedHeader;
    }


    @Override
    protected String getStringParam() {
        WebElement headElem;
        try {
            headElem = browser.findElement(By.tagName("head"));
            return headElem.getAttribute("innerHTML");
        } catch (NoSuchElementException e) {
            return "";
        }
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatHeader, String header) {

        assertThatHeader.overridingErrorMessage("Expecting web page header to contain '%s', but was '%s'",
                expectedHeader, header).contains(expectedHeader);
    }


}
