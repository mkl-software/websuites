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
import java.util.HashMap;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.internal.command.impl.validator.CommandSchemaValidator;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;



@RunWith(JUnitParamsRunner.class)
public class CommandSchemaValidatorTest {


    private CommandSchemaValidator sut;


    @Rule
    public FluentExpectedException expectedException = FluentExpectedException.none();



    @Test
    public void shouldPassValidationWhenNoValidationRules() {
        // given
        sut =
                new CommandSchemaValidator(SchemaValidationRule.emptyValidationRules(),
                        new ArrayList<ParameterValueValidator>());
        Map<String, String> someMap = new HashMap<String, String>();
        // and
        someMap.put("paramOne", "value one");
        someMap.put("paramTwo", "value 2");
        someMap.put("param3", "value 3");
        // when
        sut.validateCommandSchema(someMap);
        sut.validateCommandSchema(new HashMap<String, String>());
        // then no exception expected

    }



    @Test
    public void shouldPassValidationOneTopParamNoOthers() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        sut = new CommandSchemaValidator(rule);
        Map<String, String> someMap = new HashMap<String, String>();
        // and
        someMap.put("topElem", "some value doesn't matter what");
        // when
        sut.validateCommandSchema(someMap);
        // then no exception expected
    }


    @Test(expected = WebSuitesException.class)
    public void shouldNotPassValidationOneTopParamNoOthers() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        sut = new CommandSchemaValidator(rule);
        Map<String, String> someMap = new HashMap<String, String>();
        // and
        someMap.put("topElem", "some value doesn't matter what");
        someMap.put("unwanted", "some value doesn't matter what");
        // when
        sut.validateCommandSchema(someMap);
        // then no exception expected
    }



    @Test
    public void shouldPassValidationOneTopOneRequiredNoOptional() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("additionalRequired");
        sut = new CommandSchemaValidator(rule);
        Map<String, String> someMap = new HashMap<String, String>();
        // and
        someMap.put("topElem", "some value doesn't matter what");
        someMap.put("additionalRequired", "some value doesn't matter what");
        // when
        sut.validateCommandSchema(someMap);
        // then no exception expected
    }



    @Test
    public void shouldNotPassValidationOneTopOneRequiredNoOptional1() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("required");
        sut = new CommandSchemaValidator(rule);
        Map<String, String> someMap = new HashMap<String, String>();
        // and
        someMap.put("topElem", "some value doesn't matter what");
        someMap.put("notExpectedElem", "some value doesn't matter what");
        // then
        expectValidationException("[required]", "[]");
        sut.validateCommandSchema(someMap);
    }


    @Test
    public void shouldNotPassValidationOneTopOneRequiredNoOptional2() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("required");
        sut = new CommandSchemaValidator(rule);
        Map<String, String> someMap = new HashMap<String, String>();
        // and
        someMap.put("topElem", "some value doesn't matter what");
        someMap.put("required", "some value doesn't matter what");
        someMap.put("notExpectedElem", "some value doesn't matter what");
        // then
        expectValidationException("[required]", "[]");
        sut.validateCommandSchema(someMap);
    }



    @Test
    public void shouldPassValidationOneRequiredOneOptional1() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("required");
        rule.addOptionalElements("optional");
        sut = new CommandSchemaValidator(rule);
        Map<String, String> someMap = new HashMap<String, String>();
        // and
        someMap.put("topElem", "some value doesn't matter what");
        someMap.put("required", "some value doesn't matter what");
        someMap.put("optional", "some value doesn't matter what");
        // when
        sut.validateCommandSchema(someMap);
        // then no exception expected
    }


    @Test
    public void shouldPassValidationOneRequiredOneOptional2() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("required");
        rule.addOptionalElements("optional");
        sut = new CommandSchemaValidator(rule);
        Map<String, String> params = new HashMap<String, String>();
        // and
        params.put("topElem", "some value doesn't matter what");
        params.put("required", "some value doesn't matter what");
        // when
        sut.validateCommandSchema(params);
        // then no exception expected
    }



    @Test
    public void shouldNotPassValidationOneRequiredOneOptional1() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("required");
        rule.addOptionalElements("optional");
        sut = new CommandSchemaValidator(rule);
        Map<String, String> params = new HashMap<String, String>();
        // and
        params.put("topElem", "some value doesn't matter what");
        params.put("optional", "some value doesn't matter what");
        // then
        expectValidationException("[required]", "[optional]");
        sut.validateCommandSchema(params);
    }


    @Test
    public void shouldNotPassValidationOneRequiredOneOptional2() {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("required");
        rule.addOptionalElements("optional");
        sut = new CommandSchemaValidator(rule);
        // and
        Map<String, String> params = new HashMap<String, String>();
        params.put("topElem", "some value doesn't matter what");
        params.put("required", "some value doesn't matter what");
        params.put("not optional", "some value doesn't matter what");
        // then
        expectValidationException("[required]", "[optional]");
        sut.validateCommandSchema(params);
    }



    @Parameters({"", "optional1;optional2;optional3", "optional1", "optional2", "optional3", "optional3;optional1",
            "optional2;optional3", "optional1;optional3"})
    @Test
    public void shouldPassValidationManyRequired(String optionals) {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("required1");
        rule.addMandatoryElements("required2");
        rule.addMandatoryElements("required3");
        rule.addOptionalElements("optional1");
        rule.addOptionalElements("optional2");
        rule.addOptionalElements("optional3");
        sut = new CommandSchemaValidator(rule);
        // and
        Map<String, String> params = new HashMap<String, String>();
        params.put("topElem", "some value doesn't matter what");
        params.put("required1", "some value doesn't matter what");
        params.put("required2", "some value doesn't matter what");
        params.put("required3", "some value doesn't matter what");
        String[] options = optionals.split(";");
        for (String optional : options) {
            if (!optional.isEmpty()) {
                params.put(optional, "some value doesn't matter what");
            }
        }
        // then
        sut.validateCommandSchema(params);
    }



    @Parameters({"required1", "required2", "required3", "required3;required1", "required2;required3",
            "required1;required3"})
    @Test
    public void shouldNotPassValidationManyRequired(String required) {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        rule.addMandatoryElements("required1");
        rule.addMandatoryElements("required2");
        rule.addMandatoryElements("required3");
        sut = new CommandSchemaValidator(rule);
        // and
        Map<String, String> params = new HashMap<String, String>();
        params.put("topElem", "some value doesn't matter what");
        String[] requiredParams = required.split(";");
        for (String param : requiredParams) {
            if (!param.isEmpty()) {
                params.put(param, "some value doesn't matter what");
            }
        }
        // then
        expectValidationException("[required1, required2, required3]", "[]");
        sut.validateCommandSchema(params);
    }



    private void expectValidationException(String requiredParams, String optionalParams) {

        expectedException.expect(WebSuitesException.class).hasMessageStartingWith("Given parameters")
                .hasMessageContaining("don't match command allowed parameters").hasMessageContaining(requiredParams)
                .hasMessageContaining(optionalParams);
    }



    @Parameters({"", "not numeric", "--2", "dd2", "34sfe"})
    @Test
    public void shouldNotPassValidationInvalidParamNumericValueNotNumber(String paramValue) {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        sut = new CommandSchemaValidator(rule);
        sut.addParameterValueValidator(new IntegerNumberParamValidator("topElem"));
        // and
        Map<String, String> params = new HashMap<String, String>();
        params.put("topElem", paramValue);
        // and
        expectedException.expect(WebSuitesException.class).hasMessageStartingWith("Parameter topElem")
                .hasMessageContaining("must be proper integer value");
        // then
        sut.validateCommandSchema(params);
    }


    @Parameters({"12", "0", "3", "2135323233", "24324234"})
    @Test
    public void shouldPassValidationParamNumericValue(String paramValue) {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        sut = new CommandSchemaValidator(rule);
        sut.addParameterValueValidator(new IntegerNumberParamValidator("topElem"));
        // and
        Map<String, String> params = new HashMap<String, String>();
        params.put("topElem", paramValue);
        // then
        sut.validateCommandSchema(params);
    }



    @Parameters({"-12", "-0", "-3", "-200", "-300"})
    @Test
    public void shouldPassValidationParamNumericNegativeValue(String paramValue) {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        sut = new CommandSchemaValidator(rule);
        sut.addParameterValueValidator(new IntegerNumberParamValidator("topElem", -500, 1));
        // and
        Map<String, String> params = new HashMap<String, String>();
        params.put("topElem", paramValue);
        // then
        sut.validateCommandSchema(params);
    }


    @Parameters({"-12,0,-3", "-1,3,2", "100,200,199", "100,200,200", "100,200,100"})
    @Test
    public void shouldPassValidationParamNumericValueBetween(int a, int b, int value) {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        sut = new CommandSchemaValidator(rule);
        sut.addParameterValueValidator(new IntegerNumberParamValidator("topElem", a, b));
        // and
        Map<String, String> params = new HashMap<String, String>();
        params.put("topElem", value + "");
        // then
        sut.validateCommandSchema(params);
    }



    @Parameters({"-12,0,-13", "-1,3,4", "100,200,2000000", "100,200,99", "100,200,-100"})
    @Test
    public void shouldNotPassValidationParamNumericValueBetween(int a, int b, int value) {
        // given
        SchemaValidationRule rule = new SchemaValidationRule("topElem");
        sut = new CommandSchemaValidator(rule);
        sut.addParameterValueValidator(new IntegerNumberParamValidator("topElem", a, b));
        // and
        Map<String, String> params = new HashMap<String, String>();
        params.put("topElem", value + "");
        // and
        expectedException.expect(WebSuitesException.class).hasMessage(
                "Integer value for param topElem must be between " + a + " and " + b);
        // then
        sut.validateCommandSchema(params);
    }
}
