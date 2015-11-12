/**
 * Copyright 2015 MKL Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mkl.websuites.internal.command.impl.key;

import java.util.Arrays;
import java.util.Locale;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.WebSuitesException;


@CommandDescriptor(name = "press", argumentTypes = {String.class})
public class PressCommand extends BaseCommand {

    private String keyCombination;


    public PressCommand(String keyCombination) {
        this.keyCombination = keyCombination;
    }

    @Override
    protected void runStandardCommand() {
        Actions action = new Actions(browser);
        String[] keyTokens = keyCombination.trim().toUpperCase(Locale.getDefault()).split("-");
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
            return key.toLowerCase(Locale.getDefault());
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
