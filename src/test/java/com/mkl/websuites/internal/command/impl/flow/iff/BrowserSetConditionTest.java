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

import static org.assertj.core.api.Assertions.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class BrowserSetConditionTest {


    private BrowserSetCondition sut;



    @Test
    public void shouldAcceptInOneElementSet() {
        // given
        sut = new BrowserSetCondition("chrome") {
            @Override
            protected String currentBrowser() {
                return "chrome";
            }
        };
        // when
        boolean conditionMet = sut.isConditionMet();
        // then
        assertThat(conditionMet).isTrue();
    }



    @Parameters({"chrome", "ie", "ff", "safari"})
    @Test
    public void shouldAcceptInElementSet(final String curentBrowser) {
        // given
        sut = new BrowserSetCondition("chrome,ie,ff,safari") {
            @Override
            protected String currentBrowser() {
                return curentBrowser;
            }
        };
        // when
        boolean conditionMet = sut.isConditionMet();
        // then
        assertThat(conditionMet).isTrue();
    }



    @Parameters({"Chrome", "IE", "ffox", ""})
    @Test
    public void shouldNotAcceptInElementSet(final String curentBrowser) {
        // given
        sut = new BrowserSetCondition("chrome,ie,ff,safari") {
            @Override
            protected String currentBrowser() {
                return curentBrowser;
            }
        };
        // when
        boolean conditionMet = sut.isConditionMet();
        // then
        assertThat(conditionMet).isFalse();
    }
}
