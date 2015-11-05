package com.mkl.websuites.internal.command.impl.wait;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "waitUntilAlert")
public class WaitForAlertCommand extends BaseCommand {



    @Override
    protected void runStandardCommand() {

        WebDriverWait wait = new WebDriverWait(browser, webElementWaitTimeout);

        wait.until(ExpectedConditions.alertIsPresent());
    }

}
