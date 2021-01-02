package com.github.seguri.spring_oauth2.ac;

public class HealthAdvice {

  private String advice;
  private String username;

  private HealthAdvice(Builder builder) {
    advice = builder.advice;
    username = builder.username;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getAdvice() {
    return advice;
  }

  public String getUsername() {
    return username;
  }

  public static final class Builder {

    private String advice;
    private String username;

    private Builder() {}

    public Builder withAdvice(String val) {
      advice = val;
      return this;
    }

    public Builder withUsername(String val) {
      username = val;
      return this;
    }

    public HealthAdvice build() {
      return new HealthAdvice(this);
    }
  }
}
