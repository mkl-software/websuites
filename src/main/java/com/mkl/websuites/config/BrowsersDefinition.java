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
package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.config.BrowserConfig.BrowserType;


/**
 * Default definitions for browsers that don't need any additional drivers and come OOTB.
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BrowsersDefinition {

    BrowserConfig[] browsers() default {

    @BrowserConfig(browserType = BrowserType.FIREFOX, id = "ff", displayName = "Firefox", webDriverPath = ""),

    @BrowserConfig(browserType = BrowserType.NONE, id = "none", displayName = "None", webDriverPath = ""),

    @BrowserConfig(browserType = BrowserType.HTML, id = "html", displayName = "HTML Unit", webDriverPath = "")};
}
