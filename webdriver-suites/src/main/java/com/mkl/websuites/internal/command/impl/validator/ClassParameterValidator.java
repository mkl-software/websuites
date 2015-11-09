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

public abstract class ClassParameterValidator implements ParameterValueValidator {

    public ClassParameterValidator() {
        super();
    }

    @Override
    public void validateParam(String paramValue) {

        try {
            Class<?> classToInstantiate = Class.forName(paramValue);
            Class<?> targetExpectedSuperClass = getTargetClassForInstantiation();
            if (!targetExpectedSuperClass.isAssignableFrom(classToInstantiate)) {
                throw new WebSuitesException("Specified class " + paramValue + " must implement "
                        + targetExpectedSuperClass.getName());
            }
            classToInstantiate.newInstance();
        } catch (ClassNotFoundException e) {
            throw new WebSuitesException("Cannot find specified class: " + paramValue);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new WebSuitesException("Cannot instantiate specified class: " + paramValue
                    + " Make sure the class has a no-arg constructor.");
        }

    }

    protected abstract Class<?> getTargetClassForInstantiation();

}
