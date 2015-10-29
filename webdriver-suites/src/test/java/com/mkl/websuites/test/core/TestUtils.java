package com.mkl.websuites.test.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import mockit.Deencapsulation;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.mkl.websuites.internal.tests.ScenarioFileTest;

public class TestUtils {

	
	
	public static void checkIfNoFailures(Result result) {
		if (result.getFailureCount() > 0) {
			
			System.out.println(result.getFailures());
			
			StringBuffer sb = new StringBuffer();
			
			for (Failure failure : result.getFailures()) {
				sb.append("[" + failure.getTestHeader() + "] ")
					.append(failure.getMessage());
					
			}
			
			fail("There are failurs in the unerlying test being invoked, see "
					+ "details below:\n" + sb.toString());
		}
	}
	
	
	public static void checkCorrectResultRunsCount(Result result, int runCount) {
		
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(runCount);
		
		assertThat(result.getIgnoreCount()).isZero();
	}
	
	
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static void overrideScenarioFileNameAnnotation(Class<?> annotatedClass,
//			Class<? extends Annotation> annotationClass, Annotation newValue) 
//			throws Throwable {
//		
//		Field field = Class.class.getDeclaredField("annotations");
//	    field.setAccessible(true);
//		Map<Class<? extends Annotation>, Annotation> annotations = (Map) field.get(annotatedClass);
//		
//		
//		annotations.put(annotationClass, newValue);
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void overrideScenarioFileNameAnnotation(Class<?> annotatedClass,
			Class<? extends Annotation> annotationClass,
			Annotation newValue) 
			throws Throwable {
		
			annotatedClass.getAnnotation(annotationClass);
		
		Field field = Class.class.getDeclaredField("annotations");
	    field.setAccessible(true);
		Map<Class<? extends Annotation>, Annotation> annotations = (Map) field.get(annotatedClass);
		
		
		annotations.put(annotationClass, newValue);
	}



	public static Annotation originalAnnotationFor(Class<?> annotatedClass,
			Class<? extends Annotation> annotationClass) {
		
		final Annotation originalAnnotation = annotatedClass.getAnnotation(annotationClass);
		
		return originalAnnotation;
	}
	
	
	public static void prepareMockScenarioFileName(final String scenarioName) throws Throwable {
		
		new MockUp<ScenarioFileTest>() {
			
			@Mock
			List<junit.framework.Test> defineTests(Invocation invocation) {
				
				Deencapsulation.setField(invocation.getInvokedInstance(),
						"genericParams", new Object[] {scenarioName});
				
				return invocation.proceed();
			}
		};
	}
}
