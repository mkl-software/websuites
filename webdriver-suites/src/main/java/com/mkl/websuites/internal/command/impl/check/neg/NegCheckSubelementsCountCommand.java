package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSubelementsCountCommand;


@CommandDescriptor(name = "~checkSubelementsCount", argumentTypes = {String.class, String.class, Integer.class})
public class NegCheckSubelementsCountCommand extends
		CheckSubelementsCountCommand {

	public NegCheckSubelementsCountCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckSubelementsCountCommand(String selector,
			String subElementSelector, Integer expectedNumberOfElements) {
		super(selector, subElementSelector, expectedNumberOfElements);
	}

	protected class NegCheckSubelementsCount extends CheckSubelementsCount {

		@Override
		protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {
			
			assertion
				.overridingErrorMessage("Expecting number of elements picked by selector '%s' under element "
						+ "picked by selector '%s' NOT to be %s ",
						subelementsSelector, by, expectedNumberOfElements)
				.isNotEqualTo(expectedNumberOfElements);
		}
	}
	
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckSubelementsCount();
	}
}
