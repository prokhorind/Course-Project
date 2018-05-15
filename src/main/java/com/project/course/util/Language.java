package com.project.course.util;

import java.util.Locale;
import java.util.ResourceBundle;



/**
 * Created by kleba on 09.05.2018.
 */
public class Language {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("text",new Locale("en","EN"));


    public static void setNewLocale(Locale locale){
        resourceBundle = ResourceBundle.getBundle("text",locale);
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}
