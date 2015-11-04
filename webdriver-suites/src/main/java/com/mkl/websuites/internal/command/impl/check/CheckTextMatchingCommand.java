package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.BooleanAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;



/**
 * IMPORTANT: this command will not wait for the element with the explicit timeout,
 * it will check the elements that are visible/existing on the page in the moment of its execution.
 * @author klosinskim
 *
 */
@CommandDescriptor(name = "checkTextMatching", argumentTypes = String.class)
public class CheckTextMatchingCommand extends AbstractCheck {


	protected String regex;
	
	protected WebElement foundElem;

	public CheckTextMatchingCommand(String regex) {
		this.regex = regex;
	}
	
	@Override
	protected Object[] getAssertionsParameters() {
		// select all text nodes:
		List<WebElement> pageElements = browser.findElements(By.xpath("//body//*"));
		boolean isMatch = false;
		for (WebElement webElement : pageElements) {
			String text = webElement.getText();
			if (text.matches(regex)) {
				isMatch = true;
				foundElem = webElement;
				break;
			}
		}
		return new Object[] {isMatch};
	}
	
	@Override
	protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {
		
		((BooleanAssert) assertion)
			.overridingErrorMessage("The regular expression '%s' does not match any text in any of the "
					+ "currently visible elements on the page" , regex)
			.isTrue();
	}


	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		boolean isMatch = (boolean) args[0];
		return assertThat(isMatch);
	}
}
