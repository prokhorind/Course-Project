package com.project.course.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kleba on 18.05.2018.
 */
public class ValidationTest {

    @Test
    public void isLoginValid() throws Exception {
        String login="login";
      assertTrue(Validation.isLoginValid(login));
    }

    @Test
    public void isRussianLoginValid() throws Exception {
        String login="Логин";
        assertTrue(Validation.isLoginValid(login));
    }

    @Test
    public void isSymbolLoginValid() throws Exception {
        String login="Ch@$$ck";
        assertFalse(Validation.isLoginValid(login));
    }

    @Test
    public void injectionProtection() throws Exception {
        String protectString = "&lt;h1&gt;hello&lt;/hello&gt;";
        assertEquals(protectString,Validation.injectionProtection("<h1>hello</hello>"));
    }

    @Test
    public void isPasswordValid() throws Exception {
            assertTrue(Validation.isPasswordValid("Passw0rd"));
    }
    @Test
    public void isPasswordValidWithoutNumber() throws Exception {
        assertFalse(Validation.isPasswordValid("Password"));
    }
    @Test
    public void isPasswordValidWithoutUpperCase() throws Exception {
        assertFalse(Validation.isPasswordValid("passw0rd"));
    }

    @Test
    public void isEmailValid() throws Exception {
        assertTrue(Validation.isEmailValid("mail@gmail.com"));
    }

    @Test
    public void isFakeEmailValid() throws Exception {
        assertFalse(Validation.isEmailValid("mailgmail.com"));
    }


}