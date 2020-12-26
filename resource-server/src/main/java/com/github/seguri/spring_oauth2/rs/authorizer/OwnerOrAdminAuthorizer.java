package com.github.seguri.spring_oauth2.rs.authorizer;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OwnerOrAdminAuthorizer {

  public boolean isOwnerOrAdmin(String username) {
    if (StringUtils.isEmpty(username)) {
      return false;
    }
    var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return username.equals(jwt.getClaims().get("user_name"));
  }
}
