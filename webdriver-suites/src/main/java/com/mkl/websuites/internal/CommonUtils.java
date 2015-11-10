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
package com.mkl.websuites.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mkl.websuites.WebSuitesUserProperties;

public class CommonUtils {


    public static String populateStringWithProperties(String origValue) {
        String populated = new String(origValue);
        String regex = "\\$\\{(.*?)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(origValue);
        while (matcher.find()) {
            String propName = matcher.group(1);
            String value = WebSuitesUserProperties.get().getProperty(propName);
            if (value != null) {
                populated = populated.replaceAll("\\$\\{" + propName + "\\}", value);
            }
        }
        return populated;
    }
    
    
    public static String normalizeUrlPath(String protocol, String host, int portNumber, String basePath) {

//      host = host.matches("[a-z]+:///?.*") ? host : "http://" + host;
      host = protocol + "://" + host;
      String port = portNumber == 80 ? "" : ":" + portNumber;
      String path = basePath;
      path = path.startsWith("/") || path.isEmpty() ? path : "/" + path;
      String url = host + port + path;
      // normalize "/" but after http:// section:
      url = url.substring(0, 7) + url.substring(7).replaceAll("//", "/");
      return url;
  }
}
