package com.mkl.websuites.internal.command.impl.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.internal.command.impl.flow.RepeatDataProvider;

public class LocalEmptyDataProvider implements RepeatDataProvider {

		@Override
		public List<Map<String, String>> provideData() {
			return new ArrayList<Map<String,String>>();
		}
		
}