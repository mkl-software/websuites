package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckCommand;


@CommandDescriptor(name = "~softCheck", argumentTypes = String.class)
public class NegSoftCheckCommand extends NegCheckCommand {

    public NegSoftCheckCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckCommand(String elemement) {
        super(elemement);
    }


    // can't override "fail" method because then in OperationOnWebElement the
    // fail would be called from here and it can't be soft.
    @Override
    protected void localFail(String message) {
        CommandUtils.softFail(softly, message);
    }


}
