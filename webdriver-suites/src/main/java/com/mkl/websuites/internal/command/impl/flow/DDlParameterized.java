package com.mkl.websuites.internal.command.impl.flow;

import java.util.List;

public interface DDlParameterized {


    List<String> getSubTestCaseNames();

    void runForDDlParam(int paramIndex);
}
