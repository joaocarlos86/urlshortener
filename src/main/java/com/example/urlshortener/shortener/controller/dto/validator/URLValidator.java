package com.example.urlshortener.shortener.controller.dto.validator;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class URLValidator implements ConstraintValidator<URL, String> {

  private String[] schemes = {"http","https"};
  private UrlValidator urlValidator = new UrlValidator(schemes);

  @Override
  public boolean isValid(String uri, ConstraintValidatorContext constraintValidatorContext) {
    if (ObjectUtils.isEmpty(uri)) {
      return false;
    }

    if (uri.contains("://")) {
      return urlValidator.isValid(uri);
    } else {
      return urlValidator.isValid("http://"+uri);
    }
  }
}
