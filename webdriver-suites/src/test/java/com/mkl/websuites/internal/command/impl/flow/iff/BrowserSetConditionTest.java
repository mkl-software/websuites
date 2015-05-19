package com.mkl.websuites.internal.command.impl.flow.iff;

import static org.assertj.core.api.Assertions.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class BrowserSetConditionTest {

	
	private BrowserSetCondition sut;
	
	
	
	@Test
	public void shouldAcceptInOneElementSet() {
		//given
		sut = new BrowserSetCondition("chrome") {
			@Override
			protected String currentBrowser() {
				return "chrome";
			}
		};
		//when
		boolean conditionMet = sut.isConditionMet();
		//then
		assertThat(conditionMet).isTrue();
	}
	
	
	
	
	@Parameters({"chrome", "ie", "ff", "safari"})
	@Test
	public void shouldAcceptInElementSet(final String curentBrowser) {
		//given
		sut = new BrowserSetCondition("chrome,ie,ff,safari") {
			@Override
			protected String currentBrowser() {
				return curentBrowser;
			}
		};
		//when
		boolean conditionMet = sut.isConditionMet();
		//then
		assertThat(conditionMet).isTrue();
	}
	
	
	
	@Parameters({"Chrome", "IE", "ffox", ""})
	@Test
	public void shouldNotAcceptInElementSet(final String curentBrowser) {
		//given
		sut = new BrowserSetCondition("chrome,ie,ff,safari") {
			@Override
			protected String currentBrowser() {
				return curentBrowser;
			}
		};
		//when
		boolean conditionMet = sut.isConditionMet();
		//then
		assertThat(conditionMet).isFalse();
	}
}
