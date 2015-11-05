package com.mkl.websuites.internal.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.impl.validator.CommandSchemaValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;
import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public abstract class ParameterizedCommand extends BaseCommand {



    protected Map<String, String> parameterMap;

    private boolean propertiesAlreadyPopulated = false;

    protected Map<String, String> propsBeforeSubstitution;



    public ParameterizedCommand(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }



    @Override
    public void run() {

        if (parameterMap == null) {

            super.run();

        } else {

            log.debug("running parameterized command " + this.getClass() + " with parameters " + parameterMap);

            resolvePropertyValuesInParameterMap();

            validateParameters();

            populateBrowser();

            try {
                runCommandWithParameters();

            } catch (Throwable e) {

                augmentErrorMessageWithCommandSourceFileInfo(e);
                throw e;
            }
        }
    }



    protected void populateBrowser() {
        browser = ServiceFactory.get(BrowserController.class).getWebDriver();
    }



    protected void validateParameters() {
        new CommandSchemaValidator(defineValidationRules(), defineParameterValueValidators())
                .validateCommandSchema(parameterMap);
    }


    protected void resolvePropertyValuesInParameterMap() {

        // fix for properties not being able to be resolved after first substitution
        // in the repeat statement
        if (!propertiesAlreadyPopulated) {
            // make a copy to allow substitution on next run:
            propsBeforeSubstitution = new HashMap<>(parameterMap);
            propertiesAlreadyPopulated = true;
        } else {
            parameterMap = new HashMap<>(propsBeforeSubstitution);
        }

        Map<String, String> populatedMap = new HashMap<String, String>();
        for (String key : parameterMap.keySet()) {

            String origValue = parameterMap.get(key);
            String withPopulatedProperties = populateStringWithProperties(origValue);
            populatedMap.put(key, withPopulatedProperties);

        }
        parameterMap = populatedMap;
    }



    protected abstract void runCommandWithParameters();


    protected abstract List<SchemaValidationRule> defineValidationRules();

    protected List<ParameterValueValidator> defineParameterValueValidators() {
        return new ArrayList<>();
    }



}
