package com.example.urlshortener.shortener.dto;

public class CreateShortURLResponse {
  private String originalUrl;
  private String shortUrl;

  public CreateShortURLResponse() {
  }

  public CreateShortURLResponse(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public CreateShortURLResponse(String originalUrl, String shortToken) {
    this.originalUrl = originalUrl;
    this.shortUrl = shortToken;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }
}
