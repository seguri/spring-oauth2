package com.github.seguri.spring_oauth2.rs.controllers;

import com.github.seguri.spring_oauth2.rs.controllers.dto.HealthAdvice;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advice")
public class HealthAdviceController {

  private static final Logger LOGGER = Logger.getLogger(HealthAdviceController.class.getName());

  @PostMapping
  @PreAuthorize("hasAuthority('advice')")
  public void provideHealthAdviceCallback(@RequestBody List<HealthAdvice> healthAdvice) {
    healthAdvice.forEach(
        h -> LOGGER.info("Advice for: " + h.getUsername() + " Advice text: " + h.getAdvice()));
  }
}
