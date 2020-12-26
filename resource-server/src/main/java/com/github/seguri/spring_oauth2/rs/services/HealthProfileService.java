package com.github.seguri.spring_oauth2.rs.services;

import com.github.seguri.spring_oauth2.rs.entities.HealthProfile;
import com.github.seguri.spring_oauth2.rs.exceptions.HealthProfileAlreadyExistsException;
import com.github.seguri.spring_oauth2.rs.exceptions.NonExistentHealthProfileException;
import com.github.seguri.spring_oauth2.rs.repositories.HealthProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthProfileService {

  private final HealthProfileRepository healthProfileRepository;

  public HealthProfileService(HealthProfileRepository healthProfileRepository) {
    this.healthProfileRepository = healthProfileRepository;
  }

  public void addHealthProfile(HealthProfile profile) {
    healthProfileRepository
        .findHealthProfileByUsername(profile.getUsername())
        .ifPresentOrElse(
            healthProfileRepository::save, HealthProfileAlreadyExistsException::throwDefault);
  }

  public HealthProfile findHealthProfile(String username) {
    return healthProfileRepository
        .findHealthProfileByUsername(username)
        .orElseThrow(NonExistentHealthProfileException::new);
  }

  public void deleteHealthProfile(String username) {
    healthProfileRepository
        .findHealthProfileByUsername(username)
        .ifPresentOrElse(
            healthProfileRepository::delete, NonExistentHealthProfileException::throwDefault);
  }
}
