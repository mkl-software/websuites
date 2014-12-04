package com.mkl.websuites.internal.command.impl.validator;

import java.util.List;
import java.util.Map;

import com.mkl.websuites.internal.command.impl.flow.repeat.RepeatDataProvider;

public class WithoutNoArgConstructor implements RepeatDataProvider {

	
	public WithoutNoArgConstructor(String arg) {}

	@Override
	public List<Map<String, String>> provideData() {
		return null;
	}
}
