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

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.internal.WebSuitesException;


@RunWith(JUnitParamsRunner.class)
public class BooleanParamValidatorTest {


    private BooleanParamValidator sut = new BooleanParamValidator("testParam");


    @Rule
    public FluentExpectedException expectedException = FluentExpectedException.none();


    @Parameters({"true", "false", "TRUE", "FALSE", "True", "False"})
    @Test
    public void shouldPassBooleanParamValidation(String param) {
        // given param
        // when
        sut.validateParam(param);
        // then OK
    }



    @Parameters({"", "f", "t", "yes", "no", "TRUEe", "FALSEE"})
    @Test
    public void shouldNotPassBooleanParamValidation(String param) {
        // given
        expectedException.expect(WebSuitesException.class).hasMessageContaining("must be proper BOOLEAN value")
                .hasMessageContaining("testParam").hasMessageContaining(param);
        // then
        sut.validateParam(param);
    }


    @Test
    public void shouldNotPassForNullValue() {
        // given
        expectedException.expect(WebSuitesException.class);
        // then
        sut.validateParam(null);
    }
}
