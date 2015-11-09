/**
 * Copyright 2015 MKL Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
        result.add(new HashMap<String, String>() {
            {
                put("x", "1");
                put("y", "2");
                put("z", "3");
            }
        });
        result.add(new HashMap<String, String>() {
            {
                put("x", "p");
                put("y", "q");
                put("z", "r");
            }
        });
        result.add(new HashMap<String, String>() {
            {
                put("v1", "20");
                put("v2", "30");
                put("v3", "50");
            }
        });
        return result;
    }

}
