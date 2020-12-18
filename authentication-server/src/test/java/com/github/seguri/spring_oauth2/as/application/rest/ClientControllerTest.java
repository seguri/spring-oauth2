package com.github.seguri.spring_oauth2.as.application.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.seguri.spring_oauth2.as.domain.Client;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

  @Autowired private MockMvc mvc;

  @Test
  public void createClientTest() throws Exception {
    Client testClient = new Client("testClientId", "testClientPassword", "testScope");

    mvc.perform(
            post("/clients")
                .content(new ObjectMapper().writeValueAsString(testClient))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.clientId").value("testClientId"))
        .andExpect(jsonPath("$.scope").value("testScope"));

    mvc.perform(get("/clients"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[?(@.clientId == 'testClientId')]").exists());
  }
}
