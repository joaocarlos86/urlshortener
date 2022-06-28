package com.example.urlshortener.shortener.controller.dto.validator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class URLValidatorTest {

  private URLValidator validator = new URLValidator();

  @Test
  void isValid_givenUrlNoProtocolIsInformed_shouldAssumeHttp() {
    boolean valid = validator.isValid("www.google.com", null);
    assertThat(valid).isTrue();
  }

  @Test
  void isValid_givenUrlHasQueryStrings_shouldSucceed() {
    boolean valid = validator.isValid("www.google.com?param1=adf&param2=asd%20", null);
    assertThat(valid).isTrue();
  }

  @Test
  void isValid_givenInvalidProtocolIsInformed_shouldFail() {
    boolean valid = validator.isValid("hkkt://www.google.com", null);
    assertThat(valid).isFalse();
  }

  @Test
  void isValid_givenUrlIsMalformed_shouldFail() {
    boolean valid = validator.isValid("www.google.com param1=adf&param2=asd%20", null);
    assertThat(valid).isFalse();
  }

  @Test
  void isValid_givenUrlIsEmpty_shouldFail() {
    boolean valid = validator.isValid(null, null);
    assertThat(valid).isFalse();

    valid = validator.isValid("", null);
    assertThat(valid).isFalse();
  }
}