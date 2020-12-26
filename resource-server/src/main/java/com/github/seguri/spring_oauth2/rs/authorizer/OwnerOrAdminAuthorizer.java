package com.github.seguri.spring_oauth2.rs.authorizer;

import java.util.List;
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
    var isOwner = username.equals(jwt.getClaims().get("user_name"));
    var authorities = (List<String>) jwt.getClaims().get("authorities");
    var isAdmin = authorities.stream().anyMatch(s -> s.equals("write"));
    return isOwner || isAdmin;
  }
}
