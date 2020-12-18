package com.github.seguri.spring_oauth2.rs.services;

import com.github.seguri.spring_oauth2.rs.entities.HealthMetric;
import com.github.seguri.spring_oauth2.rs.entities.HealthProfile;
import com.github.seguri.spring_oauth2.rs.exceptions.NonExistentHealthProfileException;
import com.github.seguri.spring_oauth2.rs.repositories.HealthMetricRepository;
import com.github.seguri.spring_oauth2.rs.repositories.HealthProfileRepository;
import java.util.List;
import java.util.Optional;
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

  public void addHealthMetric(HealthMetric healthMetric) {
    Optional<HealthProfile> profile =
        healthProfileRepository.findHealthProfileByUsername(
            healthMetric.getProfile().getUsername());

    profile.ifPresentOrElse(
        p -> {
          healthMetric.setProfile(p);
          healthMetricRepository.save(healthMetric);
        },
        () -> {
          throw new NonExistentHealthProfileException("The profile doesn't exist");
        });

    ;
  }

  public List<HealthMetric> findHealthMetricHistory(String username) {
    return healthMetricRepository.findHealthMetricHistory(username);
  }

  public void deleteHealthMetricForUser(String username) {
    Optional<HealthProfile> profile = healthProfileRepository.findHealthProfileByUsername(username);

    profile.ifPresentOrElse(
        healthMetricRepository::deleteAllForUser,
        () -> {
          throw new NonExistentHealthProfileException("The profile doesn't exist");
        });
  }
}
