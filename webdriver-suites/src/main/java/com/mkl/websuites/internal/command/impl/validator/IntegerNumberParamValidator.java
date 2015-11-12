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

public class IntegerNumberParamValidator extends SingleValueValidator {


    private int lowerRange;
    private int upperRange;


    public IntegerNumberParamValidator(String paramName) {
        this(paramName, 0, Integer.MAX_VALUE);
    }

    public IntegerNumberParamValidator(String param, int upperRange) {
        this(param, 0, upperRange);
    }

    public IntegerNumberParamValidator(String param, int lowerRange, int upperRange) {
        super(param);
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
    }

    @Override
    public void validateParam(String paramValue) {

        try {
            Integer value = Integer.parseInt(paramValue);
            if (value < lowerRange || value > upperRange) {
                throw new WebSuitesException("Integer value for param " + paramName + " must be between " + lowerRange
                        + " and " + upperRange);
            }
        } catch (NumberFormatException e) {
            throw new WebSuitesException("Parameter " + paramName + " with value '" + paramValue
                    + "'must be proper integer value");
        }

    }

}
