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
package com.mkl.websuites.internal.command.impl.flow.repeat;

import java.util.List;
import java.util.Map;

public interface RepeatDataProvider {


    /**
     * Each entry in the list represents a map [parameter_name=value]
     * 
     * TODO: usually it will bring data overhead, because parameter names will be the same across
     * all data rows. Think how to represent this more efficiently in the returning type.
     * 
     * @return
     */
    List<Map<String, String>> provideData();
}
