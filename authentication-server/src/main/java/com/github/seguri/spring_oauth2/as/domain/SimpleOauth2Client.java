package com.github.seguri.spring_oauth2.as.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

public class SimpleOauth2Client implements ClientDetails {

  private final Client client;

  public SimpleOauth2Client(Client client) {
    this.client = client;
  }

  @Override
  public String getClientId() {
    return client.getClientId();
  }

  @Override
  public String getClientSecret() {
    return client.getClientSecret();
  }

  @Override
  public Set<String> getScope() {
    return Set.of(client.getScope());
  }

  @Override
  public Set<String> getResourceIds() {
    return null;
  }

  @Override
  public boolean isSecretRequired() {
    return false;
  }

  @Override
  public boolean isScoped() {
    return false;
  }

  @Override
  public Set<String> getAuthorizedGrantTypes() {
    return Set.of("password", "refresh_token", "client_credentials");
  }

  /**
   * The pre-defined redirect URI for this client to use during the "authorization_code" access
   * grant. Unnecessary since we are using "password" access grant.
   */
  @Override
  public Set<String> getRegisteredRedirectUri() {
    return Set.of();
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return Set.of();
  }

  @Override
  public Integer getAccessTokenValiditySeconds() {
    return 3600;
  }

  @Override
  public Integer getRefreshTokenValiditySeconds() {
    return 3600;
  }

  @Override
  public boolean isAutoApprove(String scope) {
    return true;
  }

  @Override
  public Map<String, Object> getAdditionalInformation() {
    return null;
  }
}
