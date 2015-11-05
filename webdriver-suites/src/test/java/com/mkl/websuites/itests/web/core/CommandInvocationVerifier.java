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
