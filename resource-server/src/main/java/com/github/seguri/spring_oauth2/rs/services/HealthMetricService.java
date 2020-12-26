package com.github.seguri.spring_oauth2.rs.services;

import com.github.seguri.spring_oauth2.rs.entities.HealthMetric;
import com.github.seguri.spring_oauth2.rs.exceptions.NonExistentHealthProfileException;
import com.github.seguri.spring_oauth2.rs.repositories.HealthMetricRepository;
import com.github.seguri.spring_oauth2.rs.repositories.HealthProfileRepository;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthMetricService {

  private final HealthMetricRepository healthMetricRepository;
  private final HealthProfileRepository healthProfileRepository;

  public HealthMetricService(
      HealthMetricRepository healthMetricRepository,
      HealthProfileRepository healthProfileRepository) {
    this.healthMetricRepository = healthMetricRepository;
    this.healthProfileRepository = healthProfileRepository;
  }

  @PreAuthorize("#healthMetric.profile.username == authentication.principal.claims['user_name']")
  public void addHealthMetric(HealthMetric healthMetric) {
    healthProfileRepository
        .findHealthProfileByUsername(healthMetric.getProfile().getUsername())
        .ifPresentOrElse(
            p -> {
              healthMetric.setProfile(p);
              healthMetricRepository.save(healthMetric);
            },
            NonExistentHealthProfileException::throwDefault);
  }

  @PreAuthorize("#username == authentication.principal.claims['user_name']")
  public List<HealthMetric> findHealthMetricHistory(String username) {
    return healthMetricRepository.findHealthMetricHistory(username);
  }

  public void deleteHealthMetricForUser(String username) {
    healthProfileRepository
        .findHealthProfileByUsername(username)
        .ifPresentOrElse(
            healthMetricRepository::deleteAllForUser,
            NonExistentHealthProfileException::throwDefault);
  }
}
