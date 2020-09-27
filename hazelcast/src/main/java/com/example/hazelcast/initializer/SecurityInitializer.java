package com.example.hazelcast.initializer;

import com.hazelcast.config.SecurityConfig;
import org.apache.tomcat.util.descriptor.web.SessionConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        super(SecurityConfig.class, SessionConfig.class);
    }

}
