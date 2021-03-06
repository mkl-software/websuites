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
package com.mkl.websuites;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.mkl.websuites.config.SiteConfig;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.CommonUtils;
import com.mkl.websuites.internal.WebSuitesException;


/**
 * Global properties access class. This class holds following properties:
 * <ul>
 * <li>system properties populated from System.getProperties(), accessible under <code>
 *      env.[propertyName]</code>, for example <code>env.java.version</code></li>
 * <li>properties loaded from a file defined in WebSuites.properties setting</li>
 * </ul>
 * <p>
 * To use this properties inside your scenario files, just use <code>${propertyName}</code> syntax.
 * </p>
 * <p>
 * To set properties inside your scenario files, use <code>setProps</code> command.
 * <p>
 * To use this properties in you test classes, use
 * <code>WebSuiteProperties.get().getProperty()</code> or <code>setProperty</code> methods.
 * </p>
 * 
 * @author Marcin Klosinski
 *
 */
public class WebSuitesUserProperties {



    private static WebSuitesUserProperties instance;


    private Map<String, String> globalProperties = new HashMap<String, String>();


    /**
     * Retrieves global properties.
     * 
     * @return global properties.
     */
    public static synchronized WebSuitesUserProperties get() {
        // late initialization + must be able to be rest for unit tests
        if (instance == null) {
            instance = new WebSuitesUserProperties();
        }
        return instance;

    }



    private WebSuitesUserProperties() {
        prepareProperties();
    }


    private void prepareProperties() {
        populateSystemProperties();
        populateUserFileProperties();
        populateSiteInfo();
    }


    private void populateSiteInfo() {
        SiteConfig siteConfig = WebSuitesConfig.get().site();
        setProperty("site.protocol", siteConfig.protocol());
        setProperty("site.host", siteConfig.host());
        setProperty("site.port", siteConfig.port() + "");
        setProperty("site.basePath", siteConfig.basePath());
        setProperty(
                "site",
                CommonUtils.normalizeUrlPath(siteConfig.protocol(), siteConfig.host(), siteConfig.port(),
                        siteConfig.basePath()));
    }


    private void populateUserFileProperties() {
        String fileName = WebSuitesConfig.get().propertiesFileName();
        if (!fileName.isEmpty()) {
            try (FileInputStream source = new FileInputStream(fileName)) {
                load(source);
            } catch (IOException e) {
                throw new WebSuitesException("Cannot load user properties from specified file name "
                        + "propertiesFileName=" + fileName, e);
            }
        }
    }


    private void populateSystemProperties() {
        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            globalProperties.put("env." + key, properties.getProperty(key.toString()));
        }
    }


    /**
     * Loads properties from a given input stream.
     * 
     * @param source source input stream.
     */
    public void load(InputStream source) {
        Properties props = new Properties();
        try {
            props.load(source);

            for (Object objectKey : props.keySet()) {
                String key = objectKey.toString();
                globalProperties.put(key, props.getProperty(key));
            }

        } catch (IOException e) {
            throw new WebSuitesException("Cannot load specified properties file", e);
        }
    }



    /**
     * Populates properties from a given string map.
     * 
     * @param properties string map
     */
    public void populateFrom(Map<String, String> properties) {

        for (Entry<String, String> entry : properties.entrySet()) {
            globalProperties.put(entry.getKey(), entry.getValue());
        }
    }


    /**
     * Checks if a given property is set (not null).
     * 
     * @param name property name
     * @return <code>true</code> if set
     */
    public boolean isSet(String name) {
        return globalProperties.get(name) != null;
    }



    /**
     * Reset (sets to null) the given property.
     * 
     * @param name property name
     */
    public void unset(String name) {
        globalProperties.put(name, null);
    }

    /**
     * Clears all properties in <code>the WebSuiteUserProperties</code>.
     * <p>
     * <b>Use with care!</b>
     * </p>
     */
    public void clear() {
        globalProperties.clear();
    }



    /**
     * Sets string property.
     * 
     * @param name property name
     * @param value property value
     */
    public void setProperty(String name, String value) {
        globalProperties.put(name, value);
    }


    /**
     * Gets string property.
     * 
     * @param name property name
     * @return property value
     */
    public String getProperty(String name) {
        return globalProperties.get(name);
    }

    public String getProperty(String name, String defaultValue) {
        String value = getProperty(name);
        return value != null && !value.isEmpty() ? value : defaultValue;
    }



    /**
     * Get number (integer) property. If number value can't be parsed, will throw an exception.
     * 
     * @param name property name
     * @return number value
     * @throws WebSuitesException error when numeric conversion is impossible
     */
    public int getNumberProperty(String name) {
        String value = getProperty(name);

        int numericValue;
        try {
            numericValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {

            String msg = "Error while converting numeric value for property: \"" + name + "\" with value: " + value;

            throw new WebSuitesException(msg, e);
        }
        return numericValue;
    }



    /**
     * Gets number value for a given property. If no value is set return default value.
     * 
     * @param name propety name
     * @param defaultValue default numeric value to return when property is not set
     * @return numeric value
     */
    public int getNumberProperty(String name, int defaultValue) {

        return getNumberProperty(getProperty(name, defaultValue + ""));
    }



    /**
     * Gets boolean property. If boolean value can't be parsed, will throw an exception.
     * <p>
     * Valid values is <code>true</code> and <code>false</code>, case insensitive.
     * </p>
     * 
     * @param name property name
     * @return boolean value
     * @throws WebSuitesException error when numeric conversion is impossible
     */
    public boolean getBooleanProperty(String name) {

        String value = getProperty(name);

        if (value == null) {
            throw new WebSuitesException("Null value for boolean property '" + name + "'");
        }

        if (value.equalsIgnoreCase("true")) {
            return true;
        }

        if (value.equalsIgnoreCase("false")) {
            return false;
        }

        throw new WebSuitesException("Invalid boolean value for property '" + name + "'");
    }



    /**
     * Gets boolean property. If boolean value can't be parsed, will throw an exception. If no value
     * is specified, will return given default value.
     * <p>
     * Valid values is <code>true</code> and <code>false</code>, case insensitive.
     * </p>
     * 
     * @param name property name
     * @param defaultValue default boolean value when not set
     * @return boolean value
     * @throws WebSuitesException error when numeric conversion is impossible
     */
    public boolean getBooleanProperty(String name, boolean defaultValue) {

        return getProperty(name) != null ? getBooleanProperty(name) : defaultValue;
    }


}
