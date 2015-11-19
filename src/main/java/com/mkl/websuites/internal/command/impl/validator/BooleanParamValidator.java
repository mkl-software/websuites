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

import com.mkl.websuites.internal.WebSuitesException;

public class BooleanParamValidator extends SingleValueValidator {

    public BooleanParamValidator(String parameterName) {
        super(parameterName);
    }

    @Override
    public void validateParam(String paramValue) {

        if (!"false".equalsIgnoreCase(paramValue) && !"true".equalsIgnoreCase(paramValue)) {

            throw new WebSuitesException("Parameter " + paramName + " with value '" + paramValue
                    + "'must be proper BOOLEAN value ('true' or 'false' literals)");
        }
    }

    @Override
    public String getParamName() {
        return paramName;
    }

}
