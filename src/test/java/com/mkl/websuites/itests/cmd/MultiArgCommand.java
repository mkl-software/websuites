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
package com.mkl.websuites.itests.cmd;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.command.CommandDescriptor;



@CommandDescriptor(

name = "multiArg", argumentTypes = {String.class, Integer.class, Boolean.class, Byte.class})
@SuppressWarnings("unused")
public class MultiArgCommand implements Command {


    private String string;
    private int integer;
    private boolean bool;
    private byte bytee;

    public MultiArgCommand(String string, Integer integer, Boolean bool, Byte bytee) {
        super();
        this.string = string;
        this.integer = integer;
        this.bool = bool;
        this.bytee = bytee;
    }

    @Override
    public void run() {

    }

    public byte getByte() {
        return bytee;
    }

}
