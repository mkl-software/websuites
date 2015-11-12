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
package com.mkl.websuites.command;


/**
 * Main interface for commands that can be run from scenario files.
 * <p>
 * Use rather {@link com.mkl.websuites.command.BaseCommand} as a base class for your commands
 * <p>
 * 
 * @author Marcin Klosinski
 *
 */
public interface Command {


    /**
     * Runs a command.
     */
    void run();
}
