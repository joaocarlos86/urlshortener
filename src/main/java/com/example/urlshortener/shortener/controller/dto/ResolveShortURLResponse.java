package com.example.urlshortener.shortener.controller.dto;

public class ResolveShortURLResponse {
  private String originalUrl;
  private String shortUrl;

  public ResolveShortURLResponse() {

  }

  public ResolveShortURLResponse(String originalUrl, String token) {
    this.originalUrl = originalUrl;
    this.shortUrl = token;
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
