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
}
