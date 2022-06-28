package com.example.urlshortener.shortener.controller.dto;

import com.example.urlshortener.shortener.controller.dto.validator.URL;

import javax.validation.constraints.NotBlank;

public class CreateShortURLRequest {

  @URL
  private String url;

  public CreateShortURLRequest() {
  }

  public CreateShortURLRequest(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
