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
package com.mkl.websuites.internal.scenario;

import lombok.Data;

@Data
public class SourceLine {


    private String line;

    private String file;

    private int lineNumber;

//    private SourceLine parent; // use for included macros

    
    public String printSourceInfo() {
        return String.format("Scenario file: %s%nLine number: %s%nLine: %s", file, lineNumber, line);
    }

    public SourceLine(String line, String file, int lineNumber) {
        super();
        this.line = line;
        this.file = file;
        this.lineNumber = lineNumber;
    }
}
