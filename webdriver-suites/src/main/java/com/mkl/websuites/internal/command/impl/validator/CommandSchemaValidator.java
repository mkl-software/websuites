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
package com.mkl.websuites.internal.command.impl.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.internal.WebSuitesException;



public class CommandSchemaValidator {


    private List<SchemaValidationRule> schemaValidationRules;

    private List<ParameterValueValidator> parameterValueValidators;

    public CommandSchemaValidator(List<SchemaValidationRule> schemaValidationRules,
            List<ParameterValueValidator> paramValueValidators) {

        this.schemaValidationRules = schemaValidationRules;
        this.parameterValueValidators = paramValueValidators;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CommandSchemaValidator(SchemaValidationRule... rules) {
        this(Arrays.asList(rules), (List) new ArrayList<ParameterValueValidator>());
    }



    public void validateCommandSchema(Map<String, String> parameters) {

        if (schemaValidationRules.size() == 0) {
            return;
        }

        boolean matchAtLeastOneRule = false;

        for (SchemaValidationRule rule : schemaValidationRules) {

            if (matchRule(parameters, rule)) {
                matchAtLeastOneRule = true;
                break;
            }

        }

        if (!matchAtLeastOneRule) {

            throw new WebSuitesException("Given parameters " + parameters + " don't match command "
                    + "allowed parameters. Please specifiy proper parameters following command "
                    + "documentation. Allowed parameter configurations are: " + schemaValidationRules);
        }

        for (ParameterValueValidator validator : parameterValueValidators) {

            String paramValue = parameters.get(validator.getParamName());
            if (paramValue != null) {
                validator.validateParam(paramValue);
            }
        }
    }



    public CommandSchemaValidator addParameterValueValidator(ParameterValueValidator validator) {
        parameterValueValidators.add(validator);
        return this;
    }


    private boolean matchRule(Map<String, String> parameters, SchemaValidationRule rule) {

        if (!parameters.keySet().contains(rule.getTopLevelRequiredElement())) {

            return false;
        }

        if (!checkMandatoryElements(rule, parameters)) {
            return false;
        }

        Map<String, String> fileteredForOptional = new HashMap<String, String>(parameters);
        fileteredForOptional.remove(rule.getTopLevelRequiredElement());
        for (String mandatory : rule.getMandatoryElements()) {
            fileteredForOptional.remove(mandatory);
        }

        if (!checkIfOnlyOptionalRemains(rule, fileteredForOptional)) {
            return false;
        }

        return true;
    }



    private boolean checkIfOnlyOptionalRemains(SchemaValidationRule rule, Map<String, String> parameters) {

        List<String> optionalElements = rule.getOptionalElements();
        for (String key : parameters.keySet()) {
            if (!optionalElements.contains(key)) {
                return false;
            }
        }
        return true;
    }



    private boolean checkMandatoryElements(SchemaValidationRule rule, Map<String, String> parameters) {

        List<String> mandatory = rule.getMandatoryElements();
        int counter = 0;
        int expectedCount = mandatory.size();
        for (String elem : mandatory) {
            if (parameters.containsKey(elem)) {
                counter++;
            }
        }
        return counter == expectedCount;
    }



}
