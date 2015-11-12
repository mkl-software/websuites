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
package com.mkl.websuites.internal.command.impl.flow.iff;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.WebSuitesException;

public class PropertyValueCondition implements IfCondition {


    private String propertyName;

    private ValueAcceptor valueAcceptor;


    public PropertyValueCondition(String propertyName) {
        super();
        this.propertyName = propertyName;
    }


    @Override
    public boolean isConditionMet() {
        
        if (valueAcceptor == null) {
            throw new WebSuitesException("Value acceptor must be set before checking a condition");
        }

        WebSuitesUserProperties props = WebSuitesUserProperties.get();
        String actualValue = props.getProperty(propertyName);
        if (actualValue == null) {
            actualValue = "";
        }

        return valueAcceptor.accept(propertyName, actualValue);
    }


    public void setValueAcceptor(ValueAcceptor valueAcceptor) {
        this.valueAcceptor = valueAcceptor;
    }



}
