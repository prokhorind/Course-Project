package com.project.course.filter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kleba on 20.05.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommandFilterTest {

    private CommandFilter commandFilter;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private FilterChain filterChain;
    @Mock
    private  HttpSession httpSession;

    @Before
    public void setUp() throws Exception {
        commandFilter = new CommandFilter();
        when(httpServletRequest.getRequestURI()).thenReturn("/start");
        when(httpSession.getAttribute("name")).thenReturn(null);
        when(httpSession.getAttribute("role")).thenReturn(null);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
    }

    @After
    public void tearDown() throws Exception {
        commandFilter = null;
    }

    @Test
    public void doFilter() throws Exception {
        commandFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
        Mockito.verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }
}