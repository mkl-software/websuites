package com.mkl.websuites.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.test.unit.core.NormalizePathTest;
import com.mkl.websuites.test.unit.core.PropertiesTest;



@RunWith(Suite.class)
@SuiteClasses({
	
	NormalizePathTest.class,
	PropertiesTest.class
})
public class CoreTests {

}
