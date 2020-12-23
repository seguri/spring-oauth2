package com.github.seguri.spring_oauth2.rs.controllers;

import com.github.seguri.spring_oauth2.rs.entities.HealthMetric;
import com.github.seguri.spring_oauth2.rs.services.HealthMetricService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metric")
public class HealthMetricController {

  private final HealthMetricService healthMetricService;

  public HealthMetricController(HealthMetricService healthMetricService) {
    this.healthMetricService = healthMetricService;
  }

  @PostMapping
  public void addHealthMetric(@RequestBody HealthMetric healthMetric) {
    healthMetricService.addHealthMetric(healthMetric);
  }

  /**
   * You can try injecting Authentication in the parameters. authentication.getCredentials and
   * authentication.getPrincipal are instances of org.springframework.security.oauth2.jwt.Jwt. From
   * what I'm reading on the internet, it should return a UserDetails class that exposes the
   * username field.
   */
  @GetMapping("/{username}")
  @PreAuthorize("#username == authentication.principal.username")
  public List<HealthMetric> findHealthMetrics(@PathVariable String username) {
    return healthMetricService.findHealthMetricHistory(username);
  }

  @DeleteMapping("/{username}")
  public void deleteHealthMetricForUser(@PathVariable String username) {
    healthMetricService.deleteHealthMetricForUser(username);
  }
}
