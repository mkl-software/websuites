package com.mkl.websuites.itests.sampleclient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.MultiBrowserTestCase;
import com.mkl.websuites.internal.command.impl.check.CheckTextPresentCommand;

public class SampleStandaloneTest extends MultiBrowserTestCase {


    @Override
    protected void runWebTest() {
        browser.get("http://google.com");
        WebElement queryBox = browser.findElement(By.id("lst-ib"));
        queryBox.sendKeys("selenium\n");
        new CheckTextPresentCommand("Selenium - Web Browser Automation").run();
    }

    @Override
    protected String getTestName() {
        return this.getClass().getName();
    }



}
