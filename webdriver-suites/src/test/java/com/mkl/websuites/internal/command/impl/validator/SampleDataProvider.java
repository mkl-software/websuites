package com.mkl.websuites.internal.command.impl.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.internal.command.impl.flow.repeat.RepeatDataProvider;

public class SampleDataProvider implements RepeatDataProvider {

		@SuppressWarnings("serial")
		@Override
		public List<Map<String, String>> provideData() {
			ArrayList<Map<String, String>> result = new ArrayList<Map<String,String>>();
			result.add(new HashMap<String, String>() {{
				put("x", "1");
				put("y", "2");
				put("z", "3");
			}});
			result.add(new HashMap<String, String>() {{
				put("x", "p");
				put("y", "q");
				put("z", "r");
			}});
			result.add(new HashMap<String, String>() {{
				put("v1", "20");
				put("v2", "30");
				put("v3", "50");
			}});
			return result;
		}
		
}