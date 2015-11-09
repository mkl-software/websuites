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
package com.mkl.websuites.internal.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.config.Service;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.WebSuitesException;



@Slf4j
public class ServiceFactory {



    private static boolean isInitialized = false;

    private static Map<Class<?>, Class<?>> instanceMap;



    public static void init() {

        if (isInitialized) {
            // throw new WebSuitesException("Cannot initialize ServiceFactory more than once.");
            log.warn("Trying to initialize ServiceFactory more than once");
            // TODO: throw or not WSE? not a big case to allow init more than once...
        }

        isInitialized = true;

        instanceMap = new HashMap<Class<?>, Class<?>>();

        ServiceDefinition defaultServiceDefinition =
                DefaultServiceDefinitions.class.getAnnotation(ServiceDefinition.class);

        Service[] services = defaultServiceDefinition.value();

        for (Service service : services) {
            instanceMap.put(service.service(), service.implementation());
        }


        applyServiceOverridesFrom();

        log.debug("service factory initialized");
    }



    private static void applyServiceOverridesFrom() {

        Service[] serviceOverrides = WebSuitesConfig.get().extension().serviceOverrides();

        for (Service override : serviceOverrides) {
            log.debug("applied service definition override for " + override.service() + " with impl: "
                    + override.implementation());
            instanceMap.put(override.service(), override.implementation());
        }

    }


    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> serviceClass) {

        if (!isInitialized) {
            log.error("trying to acquire service before service factory is initialized");
            throw new WebSuitesException("Trying to acquire service " + serviceClass.getName()
                    + " before service factory is initialized");
        }


        try {
            Method factoryMethod = instanceMap.get(serviceClass).getDeclaredMethod("getInstance");
            if (factoryMethod == null) {
                throw new WebSuitesException("Service " + serviceClass + " must implement public "
                        + "static getInstance() factory method to provide service instances.");
            }
            return (T) factoryMethod.invoke(null);

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {

            log.error("cannot make instance for service: " + serviceClass.getName() + ", error: "
                    + e.getLocalizedMessage());

            if (e.getCause() instanceof WebSuitesException) {
                throw (WebSuitesException) e.getCause();
            }

            throw new WebSuitesException("Cannot instantiate service: " + serviceClass.getName() + ", error: "
                    + e.getLocalizedMessage() + ". Make sure the service has public static getInstance() method.", e);

        } catch (Exception e) {

            throw new WebSuitesException("", e);
        }

    }

}
