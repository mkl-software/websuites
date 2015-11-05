package com.mkl.websuites.internal.command.impl.validator;

public abstract class SingleValueValidator implements ParameterValueValidator {

    protected String paramName;


    public SingleValueValidator(String param) {
        this.paramName = param;
    }

    @Override
    public String getParamName() {
        return paramName;
    }

}
