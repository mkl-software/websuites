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
package com.mkl.websuites.itests.web.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CommandInvocationVerifier {

    private static CommandInvocationVerifier instance = new CommandInvocationVerifier();

    public static CommandInvocationVerifier getInstance() {
        return instance;
    }


    private ConcurrentLinkedQueue<String> verificationQueue = new ConcurrentLinkedQueue<String>();


    public void clearVerificationQueue() {

        verificationQueue.clear();
    }


    public void expectInvocations(String... messages) {

        for (String msg : messages) {
            verificationQueue.offer(msg);
        }
    }

    public void verifyInvocation(String message) {

        String expected = verificationQueue.poll();

        assertThat(expected).overridingErrorMessage(
                "Expecting internal-test command invocation with argument: \"" + message + "\"").isNotNull();

        assertThat(message).isEqualTo(expected);

    }


    public boolean isQueueEmpty() {
        return verificationQueue.isEmpty();
    }


    public void checkRemaining() {

        assertThat(verificationQueue).overridingErrorMessage(
                "Expected more internal-test invocations: " + verificationQueue).isEmpty();
    }

}
