package com.mkl.websuites.internal.runner;

import lombok.extern.slf4j.Slf4j;

import org.junit.internal.runners.JUnit38ClassRunner;

import com.mkl.websuites.WebSuitesRunner;


@Slf4j
public class InternalWebSuitesRunner extends JUnit38ClassRunner {


    public InternalWebSuitesRunner(Class<? extends WebSuitesRunner> klass) throws Throwable {

        super(new WebSuitesRunner(klass).defineMasterSuite());

        log.debug("custom runner initialized for runner: " + klass);

    }


}
