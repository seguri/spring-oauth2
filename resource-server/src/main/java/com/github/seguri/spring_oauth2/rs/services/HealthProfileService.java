package com.github.seguri.spring_oauth2.rs.services;

import com.github.seguri.spring_oauth2.rs.entities.HealthProfile;
import com.github.seguri.spring_oauth2.rs.exceptions.HealthProfileAlreadyExistsException;
import com.github.seguri.spring_oauth2.rs.exceptions.NonExistentHealthProfileException;
import com.github.seguri.spring_oauth2.rs.repositories.HealthProfileRepository;
import java.util.Optional;
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
    Optional<HealthProfile> healthProfile =
        healthProfileRepository.findHealthProfileByUsername(profile.getUsername());

    if (healthProfile.isEmpty()) {
      healthProfileRepository.save(profile);
    } else {
      throw new HealthProfileAlreadyExistsException("This health profile already exists.");
    }
  }

  public HealthProfile findHealthProfile(String username) {
    Optional<HealthProfile> healthProfile =
        healthProfileRepository.findHealthProfileByUsername(username);

    return healthProfile.orElseThrow(
        () -> new NonExistentHealthProfileException("No profile found for the provided username."));
  }

  public void deleteHealthProfile(String username) {
    Optional<HealthProfile> healthProfile =
        healthProfileRepository.findHealthProfileByUsername(username);

    healthProfile.ifPresentOrElse(
        p -> healthProfileRepository.delete(p),
        () -> {
          throw new NonExistentHealthProfileException(
              "No profile found for the provided username.");
        });
  }
}
