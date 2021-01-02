package com.github.seguri.spring_oauth2.ac;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HealthAdviceFixture {

  public static List<HealthAdvice> getHealthAdvices() {
    return IntStream.range(0, 10)
        .mapToObj(HealthAdviceFixture::healthAdvice)
        .collect(Collectors.toList());
  }

  private static HealthAdvice healthAdvice(int id) {
    return HealthAdvice.newBuilder().withUsername("user" + id).withAdvice("advice" + id).build();
  }
}
