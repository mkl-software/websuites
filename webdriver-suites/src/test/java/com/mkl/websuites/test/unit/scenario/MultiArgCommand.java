package com.mkl.websuites.test.unit.scenario;

import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;



@CommandDescriptor(
		
		name = "multiArg",
		argumentTypes = {
				String.class,
				Integer.class,
				Boolean.class,
				Byte.class
		}
)
@SuppressWarnings("unused")
public class MultiArgCommand implements Command {

	
	private String string;
	private int integer;
	private boolean bool;
	private byte bytee;
	
	public MultiArgCommand(String string, Integer integer, Boolean bool, Byte bytee) {
		super();
		this.string = string;
		this.integer = integer;
		this.bool = bool;
		this.bytee = bytee;
	}

	@Override
	public void run() {

	}

}
