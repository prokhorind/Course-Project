package com.project.course.util;


import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by kleba on 09.05.2018.
 */
public class Validation {

    public static boolean  isLoginValid(String login) {
        final String regex = "[A-Zа-яА-Яa-z0-9-]+";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(login);
        return m.matches();

    }

    public static String injectionProtection(String text) {
        text = StringEscapeUtils.escapeHtml4(text);
        return text;
    }
   public static boolean  isPasswordValid(String password) {
        final String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isEmailValid(String email){
        final String regex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;" +
                ":\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String[] injectionProtection(String[] array){
        String [] newArray = new String[array.length];
        for(int i =0; i<array.length;i++) {
            newArray[i]= injectionProtection(array[i]);
        }
        return newArray;
    }
}
