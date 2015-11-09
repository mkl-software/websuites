package com.mkl.websuites.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.command.impl.validator.CommandSchemaValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;
import com.mkl.websuites.internal.services.ServiceFactory;


/**
 * Class to be extended by all commands that need to have a parameterized version.
 * <p>A parameterized version is used as folows:</p>
 * <code>myCommand  param1=value1  param2=value2</code>
 * <p>To define a schema for the parameters, override <code>defineValidationRules</code> method.
 * @author klosinskim
 *
 */
@Slf4j
public abstract class ParameterizedCommand extends BaseCommand {



    /**
     * This holds a parameters populated from the command in the scenario file.
     */
    protected Map<String, String> parameterMap;

    private boolean propertiesAlreadyPopulated = false;

    private Map<String, String> propsBeforeSubstitution;



    public ParameterizedCommand(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }



    @Override
    public final void run() {

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



    private void populateBrowser() {
        browser = ServiceFactory.get(BrowserController.class).getWebDriver();
    }



    private void validateParameters() {
        new CommandSchemaValidator(defineValidationRules(), defineParameterValueValidators())
                .validateCommandSchema(parameterMap);
    }


    private void resolvePropertyValuesInParameterMap() {

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



    /**
     * Implement to define logic for this parameterized command.
     */
    protected abstract void runCommandWithParameters();


    /**
     * Implement to define which parameters are acceptable for this command.
     * @return
     */
    protected abstract List<SchemaValidationRule> defineValidationRules();

    
    /**
     * Implement to define parameter value validators, e.g. to allow only numeric parameters.
     */
    protected List<ParameterValueValidator> defineParameterValueValidators() {
        return new ArrayList<>();
    }



}
