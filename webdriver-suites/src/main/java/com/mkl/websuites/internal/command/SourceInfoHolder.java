package com.mkl.websuites.internal.command;

import com.mkl.websuites.internal.scenario.SourceLine;

public interface SourceInfoHolder {

    SourceLine getCommandSourceLine();

    void setCommandSourceLine(SourceLine sourceLine);

}
