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

import org.junit.Rule;
import org.junit.Test;

import com.mkl.websuites.internal.WebSuitesException;

import pl.wkr.fluentrule.api.FluentExpectedException;



public class DataProviderParamValidatorTest {



    DataProviderParamValidator sut = new DataProviderParamValidator();

    @Rule
    public FluentExpectedException expectedException = FluentExpectedException.none();

    @Test
    public void shouldPassValidation() {
        // given
        // when
        sut.validateParam("com.mkl.websuites.internal.command.impl.validator.SampleDataProvider");
        // then no validation exception thrown
    }


    @Test
    public void shouldNotPassValidationClassNotFound() {
        // when
        expectedException.expect(WebSuitesException.class).hasMessageContaining("Cannot find specified class");
        // then
        sut.validateParam("com.mkl.websuites.internal.command.impl.validator.NotExisting");
    }


    @Test
    public void shouldNotPassValidationClassNotImplementingDataProvider() {
        // when
        expectedException.expect(WebSuitesException.class).hasMessageContaining("Specified class")
                .hasMessageContaining("must implement");
        // then
        sut.validateParam("com.mkl.websuites.internal.command.impl.validator." + "NotImplementingDataProvider");
    }



    @Test
    public void shouldNotPassValidationClassWithoutNoArgConstructor() {
        // when
        expectedException.expect(WebSuitesException.class).hasMessageContaining(
                "Make sure the class has a no-arg constructor");
        // then
        sut.validateParam("com.mkl.websuites.internal.command.impl.validator." + "WithoutNoArgConstructor");
    }
}
