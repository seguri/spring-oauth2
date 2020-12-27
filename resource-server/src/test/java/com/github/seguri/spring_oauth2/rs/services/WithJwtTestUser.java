package com.github.seguri.spring_oauth2.rs.services;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = JwtTestSecurityContextFactory.class)
public @interface WithJwtTestUser {
  String username();

  String[] authorities() default {"read"};
}
