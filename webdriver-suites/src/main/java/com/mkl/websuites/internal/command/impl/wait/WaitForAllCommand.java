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

	
	private static final String RETRY_TIMEOUT = "retryTimeout";
	private static final String RETRY_PAUSE = "retryPause";
	private static final String RETRY_COUNT = "retryCount";
	
	private static final int MAX_VALUE = 10000000;
	private static final int DEFAUL_RETRY_COUNT = 10;
	private static final int DEFAUL_RETRY_PAUSE = 1000;
	
	
	
	@SuppressWarnings("serial")
	public WaitForAllCommand() {
		super(new HashMap<String, String>(){{
			put(RETRY_TIMEOUT, WebSuitesConfig.get().site().waitTimeout() + "");
			put(RETRY_COUNT, DEFAUL_RETRY_COUNT + "");
			put(RETRY_PAUSE, DEFAUL_RETRY_PAUSE + "");
		}});
	}
	
	
	public WaitForAllCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}



	@Override
	protected void runCommandWithParameters() {
		
		int retryMaxCount;
		try {
			retryMaxCount = Integer.valueOf(parameterMap.get(RETRY_COUNT));
		} catch (NumberFormatException e2) {
			retryMaxCount = DEFAUL_RETRY_COUNT;
		}
		
		int retryPause;
		try {
			retryPause = Integer.valueOf(parameterMap.get(RETRY_PAUSE));
		} catch (NumberFormatException e2) {
			retryPause = DEFAUL_RETRY_PAUSE;
		}
		
		int retryTimeout = Integer.valueOf(parameterMap.get(RETRY_TIMEOUT));
		
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
		return Arrays.asList(new SchemaValidationRule(RETRY_TIMEOUT)
				.addOptionalElements(RETRY_COUNT)
				.addOptionalElements(RETRY_PAUSE));
	}
	
	
	@Override
	protected List<ParameterValueValidator> defineParameterValueValidators() {
		return Arrays.asList((ParameterValueValidator)
				new IntegerNumberParamValidator(RETRY_COUNT, -1, MAX_VALUE),
				new IntegerNumberParamValidator(RETRY_TIMEOUT, -1, MAX_VALUE),
				new IntegerNumberParamValidator("retrypPause", 0, MAX_VALUE));
	}

}
