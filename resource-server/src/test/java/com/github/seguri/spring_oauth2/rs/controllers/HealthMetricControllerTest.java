package com.github.seguri.spring_oauth2.rs.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthMetricControllerTest {

  @Autowired private MockMvc mvc;

  @Test
  public void addHealthMetricUnauthenticated() throws Exception {
    mvc.perform(delete("/metric")).andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser
  public void addHealthMetricAuthenticated() throws Exception {
    mvc.perform(delete("/metric")).andExpect(status().isForbidden());
  }
}
