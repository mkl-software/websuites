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
package com.mkl.websuites.itests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.itests.web.command.CheckTitleTest;
import com.mkl.websuites.itests.web.command.EmptyCommandIntegrationTest;
import com.mkl.websuites.itests.web.command.GotoCommandTest;
import com.mkl.websuites.itests.web.command.RepeatTimesCommandTest;
import com.mkl.websuites.itests.web.command.SelectCheckboxTest;
import com.mkl.websuites.itests.web.command.SelectCommandsTest;
import com.mkl.websuites.itests.web.command.SourceInfoTest;


/**
 * Command tests that can be run with HTMLUnit browser to quickly test in IDe.
 * 
 * @author klosinskim
 *
 */
@RunWith(Suite.class)
@SuiteClasses({EmptyCommandIntegrationTest.class, SourceInfoTest.class, GotoCommandTest.class,
        RepeatTimesCommandTest.class, CheckTitleTest.class, SelectCommandsTest.class, SelectCheckboxTest.class})
public class CommandQuickTests {
}
