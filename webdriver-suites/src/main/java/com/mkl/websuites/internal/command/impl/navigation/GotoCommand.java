package com.mkl.websuites.internal.command.impl.navigation;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.config.WebSuitesConfig;


@CommandDescriptor(name = "goto", argumentTypes = {String.class})
public class GotoCommand extends BaseCommand {


    private String address;

    public GotoCommand(String address) {
        this.address = address;
    }

    @Override
    protected void runStandardCommand() {

        if (address.startsWith("/")) {
            // relative address:
            // TODO: use a service to apply normalizePath logic
            address = WebSuitesConfig.get().site().basePath() + address;

        } else {
            if (!address.startsWith("http:")) {
                address = "http://" + address;
            }
        }

        browser.get(address);

    }

}
