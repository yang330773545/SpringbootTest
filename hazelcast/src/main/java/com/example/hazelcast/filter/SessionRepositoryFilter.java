package com.example.hazelcast.filter;


import com.example.hazelcast.wrapper.SessionRepositoryRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/*
public class SessionRepositoryFilter extends Filter {

    public doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        SessionRepositoryRequestWrapper customRequest =
                new SessionRepositoryRequestWrapper(httpRequest);

        chain.doFilter(customRequest, response, chain);
    }

    // ...
}
*/