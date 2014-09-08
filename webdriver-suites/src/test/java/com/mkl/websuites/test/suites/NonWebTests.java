package com.mkl.websuites.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.test.unit.ServiceFactoryTest;



@RunWith(Suite.class)
@SuiteClasses(
		ServiceFactoryTest.class
)
public class NonWebTests {

}
