package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.SoftAssertions;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkAllSoft")
public class CheckAllSoftCommand extends BaseCommand {

	@Override
	protected void runStandardCommand() {
		softly.assertAll();
		// reset Soft Assertions for next check
		softly = new SoftAssertions();
	}

}
