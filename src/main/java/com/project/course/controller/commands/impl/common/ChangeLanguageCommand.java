package com.project.course.controller.commands.impl.common;

import com.project.course.controller.commands.Command;
import com.project.course.util.Language;
import com.project.course.util.Validation;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by kleba on 07.05.2018.
 */
public class ChangeLanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle rb;
        String getLocale = Validation.injectionProtection(request.getParameter("lang"));
        String page = Validation.injectionProtection(request.getParameter("page"));

        if (getLocale == null || getLocale.isEmpty()) {

        } else {
            Locale locale;
            locale = new Locale(getLocale.toLowerCase(), getLocale.toUpperCase());
            Language.setNewLocale(locale);
        }
        rb= Language.getResourceBundle();
        System.out.println(rb.getLocale().getCountry());
        HttpSession session = request.getSession();
        try {
            session.setAttribute("language",rb.getLocale().getLanguage());
            response.sendRedirect(request.getContextPath()+"/pages/"+page);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
