package com.mkl.websuites.internal.command.impl.check;

import java.util.List;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkLinkHref", argumentTypes = String.class)
public class CheckLinkHrefCommand extends AbstractSingleStringCheck {

    protected WebElement actualElement;
    protected String expectedLinkText;


    public CheckLinkHrefCommand(String expectedLinkText) {
        super();
        this.expectedLinkText = expectedLinkText;
    }

    @Override
    protected String getStringParam() {

        try {
            List<WebElement> elements = browser.findElements(By.tagName("a"));
            for (WebElement elem : elements) {
                if (predicate(elem.getAttribute("href"))) {
                    actualElement = elem;
                    return "OK";
                }
            }
        } catch (NoSuchElementException e) {
            return null;
        }
        return null;
    }

    protected boolean predicate(String href) {
        return expectedLinkText.equals(href);
    }

    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage("Expecting link with HREF attribute '%s'" + " to exist", expectedLinkText)
                .isEqualTo("OK");
    }

}
