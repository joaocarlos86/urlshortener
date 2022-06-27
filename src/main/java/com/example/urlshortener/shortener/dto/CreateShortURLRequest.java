package com.example.urlshortener.shortener.dto;

public class CreateShortURLRequest {
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
