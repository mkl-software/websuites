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
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class SchemaValidationRule {


    private @Getter String topLevelRequiredElement;

    private @Getter List<String> mandatoryElements;

    private @Getter List<String> optionalElements;

    private @Getter @Setter String miniDocumentation;



    public static List<SchemaValidationRule> emptyValidationRules() {
        return Collections.emptyList();
    }


    public SchemaValidationRule(String topLevelRequiredElement) {
        super();
        this.topLevelRequiredElement = topLevelRequiredElement;
        mandatoryElements = new ArrayList<String>();
        optionalElements = new ArrayList<String>();
    }


    public SchemaValidationRule addMandatoryElements(String... elements) {
        mandatoryElements.addAll(Arrays.asList(elements));
        return this;
    }


    public SchemaValidationRule addOptionalElements(String... elements) {
        optionalElements.addAll(Arrays.asList(elements));
        return this;
    }


    @Override
    public String toString() {

        if (miniDocumentation != null && !miniDocumentation.isEmpty()) {

            return miniDocumentation;
        }

        return String.format("%nparameter \"%s\" with mandatory params %s " + "and optional params %s",
                topLevelRequiredElement, mandatoryElements.toString(), optionalElements.toString());
    }
}
