package com.mkl.websuites.internal.command;

public @interface CommandDescriptor {

	String name();

	Class<?>[] argumentTypes();

}
