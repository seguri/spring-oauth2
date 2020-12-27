package com.github.seguri.spring_oauth2.rs.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.github.seguri.spring_oauth2.rs.entities.HealthProfile;
import com.github.seguri.spring_oauth2.rs.exceptions.HealthProfileAlreadyExistsException;
import com.github.seguri.spring_oauth2.rs.repositories.HealthProfileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
public class HealthProfileServiceTest {

  @Autowired private HealthProfileService service;

  @Mock private HealthProfile profile;
  @Mock private HealthProfileRepository repository;

  @Test
  public void addHealthProfile_unauthenticated_forbidden() {
    assertThrows(
        AuthenticationCredentialsNotFoundException.class, () -> service.addHealthProfile(profile));
  }

  @Test
  @WithMockUser(authorities = "read")
  public void addHealthProfile_insufficientAuthority_forbidden() {
    assertThrows(IllegalArgumentException.class, () -> service.addHealthProfile(profile));
  }

  @Test
  @WithJwtTestUser(username = "foo")
  public void addHealthProfile_nonexistentUser_fail() {
    when(profile.getUsername()).thenReturn("john");
    assertThrows(AccessDeniedException.class, () -> service.addHealthProfile(profile));
  }

  @Test
  @WithJwtTestUser(username = "john")
  public void addHealthProfile_existingUser_fail() {
    when(profile.getUsername()).thenReturn("john");
    assertThrows(
        HealthProfileAlreadyExistsException.class, () -> service.addHealthProfile(profile));
  }

  @Test
  @WithJwtTestUser(username = "jack")
  public void addHealthProfile_existingUserWithoutProfile_ok() {
    HealthProfile healthProfile = new HealthProfile();
    healthProfile.setUsername("jack");
    when(repository.save(healthProfile)).thenReturn(healthProfile);
    HealthProfileService healthProfileService = new HealthProfileService(repository);
    healthProfileService.addHealthProfile(profile);
  }
}
