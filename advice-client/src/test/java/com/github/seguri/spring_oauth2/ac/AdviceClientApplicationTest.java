package com.github.seguri.spring_oauth2.ac;

import static com.github.seguri.spring_oauth2.ac.HealthAdviceFixture.getHealthAdvices;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdviceClientApplicationTest {

  @Autowired private ResourceServerClient sut;

  @Disabled("Requires all other services to be running")
  @Test
  public void postAdviceTest() {
    var body = getHealthAdvices();
    var response = sut.postAdvice(body);
    assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
  }
}
