package com.mkl.websuites.internal.command.impl.wait;

import static org.assertj.core.api.Assertions.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.flow.ControlFlowHandler;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;



/**
 * If both retryCount and retryTimeout are specified, the test will fail whenever any of this
 * fail condition is fist met.
 * @author m.klosinski
 *
 */
@Slf4j
@CommandDescriptor(name = "waitForAll")
public class WaitForAllCommand extends ControlFlowHandler {

	private static final int MAX_VALUE = 10000000;
	private static final int DEFAUL_RETRY_COUNT = 10;
	private static final int DEFAUL_RETRY_PAUSE = 1000;
	
	
	@SuppressWarnings("serial")
	public WaitForAllCommand() {
		super(new HashMap<String, String>(){{
			put("retryTimeout", WebSuitesConfig.get().site().waitTimeout() + "");
			put("retryCount", DEFAUL_RETRY_COUNT + "");
			put("retryPause", DEFAUL_RETRY_PAUSE + "");
		}});
	}
	
	
	public WaitForAllCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}



	@Override
	protected void runCommandWithParameters() {
		
		int retryMaxCount = Integer.valueOf(parameterMap.get("retryCount"));
		int retryPause = Integer.valueOf(parameterMap.get("retryPause"));
		int retryTimeout = Integer.valueOf(parameterMap.get("retryTimeout"));
		int retryCount = 0;
		long elapsedTime;
		
		long startTime = System.currentTimeMillis();
		
		boolean failing = true;
		
		while (failing) {
			try {
				for (Command command : nestedCommands) {
					command.run();
				}
				failing = false;
			} catch (AssertionError e) {
				retryCount++;
				log.debug("retrying {} time", retryCount);
				if (retryCount == retryMaxCount) {
					fail("Failed waiting for all nested commands to pass - max number of retry exceeded");
				}
				elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
				log.debug("elapsed time: {}", elapsedTime);
				if (elapsedTime >= retryTimeout && retryTimeout != -1) {
					fail("Failed waiting for all nested commands to pass - timeout exceeded");
				}
				try {
					Thread.sleep(retryPause);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		return Arrays.asList(new SchemaValidationRule("retryTimeout")
				.addOptionalElements("retryCount")
				.addOptionalElements("retryPause"));
	}
	
	
	@Override
	protected List<ParameterValueValidator> defineParameterValueValidators() {
		return Arrays.asList((ParameterValueValidator)
				new IntegerNumberParamValidator("retryCount", -1, MAX_VALUE),
				new IntegerNumberParamValidator("retryTimeout", -1, MAX_VALUE),
				new IntegerNumberParamValidator("retrypPause", 0, MAX_VALUE));
	}

}
