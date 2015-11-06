package com.mkl.websuites.internal.command.impl.key;

import java.util.Arrays;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "press", argumentTypes = {String.class})
public class PressCommand extends BaseCommand {

    private String keyCombination;


    public PressCommand(String keyCombination) {
        this.keyCombination = keyCombination;
    }

    @Override
    protected void runStandardCommand() {
        Actions action = new Actions(browser);
        String[] keyTokens = keyCombination.trim().toUpperCase().split("-");
        for (String key : keyTokens) {
            if (isModifier(key)) {
                action = action.keyUp(keyFromModifier(key));
            } else {
                action = action.sendKeys(keyFromString(key));
            }
        }
        action.build().perform();
    }

    private CharSequence keyFromString(String key) {
        if (key.length() == 1) {
            return key.toLowerCase();
        } else {
            try {
                return Keys.valueOf(key);
            } catch (IllegalArgumentException e) {
                throw new WebSuitesException("Wrong key identifier '" + key + "', please use '-' as key "
                        + "sesperator and as key literals one of those in the org.openqa.selenium.Keys enum");
            }
        }
    }

    private Keys keyFromModifier(String key) {
        switch (key) {
            case "CTRL" : return Keys.CONTROL;
            case "ALT" : return Keys.ALT;
            case "SHIFT" : return Keys.SHIFT;
        }
        return null;
    }

    private boolean isModifier(String key) {
        return Arrays.asList("CTRL", "SHIFT", "ALT").contains(key);
    }
    

}
