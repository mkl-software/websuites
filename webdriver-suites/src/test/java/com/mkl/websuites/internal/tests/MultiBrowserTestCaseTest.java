package com.mkl.websuites.internal.tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;
import lombok.extern.slf4j.Slf4j;
import mockit.Deencapsulation;
import mockit.MockUp;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mkl.websuites.internal.tests.MultiBrowserTestCase;



@Slf4j
@RunWith(JUnitParamsRunner.class)
public class MultiBrowserTestCaseTest {



    private static MultiBrowserTestCase logic;



    @BeforeClass
    public static void init() {

        logic = new MockUp<MultiBrowserTestCase>() {}.getMockInstance();
        log.debug("JMockit mock " + logic.getClass() + " initialized, JMockit configured properly");
    }



    @SuppressWarnings("unused")
    private Object[] parametersForTestNormalizedPath() {
        return $($("google.com", 80, "", "http://google.com"), $("http://google.com", 80, "/", "http://google.com/"),
                $("http://google.com", 80, "//", "http://google.com/"),
                $("http://google.com", 80, "resource", "http://google.com/resource"),
                $("google.com", 90, "", "http://google.com:90"), $("google.com", 90, "/", "http://google.com:90/"),
                $("https://google.com", 90, "/", "https://google.com:90/"),
                $("file:///home/root/pages/index.html", 80, "", "file:///home/root/pages/index.html"),
                $("google.com", 80, "some/path//path", "http://google.com/some/path/path"));
    }


    @Test
    @Parameters
    public void testNormalizedPath(String host, int port, String path, String expected) {

        String normalized = Deencapsulation.invoke(logic, "normalizeUrlPath", host, port, path);

        Assert.assertEquals(expected, normalized);
    }



}