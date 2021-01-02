package com.github.seguri.spring_oauth2.gateway;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

public class WireMockContextInitializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext context) {
    var wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
    wireMockServer.start();

    context.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

    context.addApplicationListener(
        applicationEvent -> {
          if (applicationEvent instanceof ContextClosedEvent) {
            wireMockServer.stop();
          }
        });

    TestPropertyValues.of("resourceServerUri=http://localhost:" + wireMockServer.port())
        .applyTo(context);
  }
}
