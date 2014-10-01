package com.mkl.websuites.internal.command.impl;

import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@Slf4j
@CommandDescriptor(name = "wait", argumentTypes = Integer.class)
public class WaitCommand extends BaseCommand {

	
	int time;
	
	
	public WaitCommand(Integer time) {
		this.time = time;
	}


	@Override
	protected void runStandardCommand() {
		log.debug("test command run");
		try {
			Thread.sleep(time);
			
		} catch (InterruptedException e) {
			Assert.fail("Wait command interrupted.");
		}
	}


}
