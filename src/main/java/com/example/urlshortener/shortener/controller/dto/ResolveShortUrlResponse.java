package com.example.urlshortener.shortener.controller.dto;

public class ResolveShortUrlResponse {
  private String originalUrl;
  private String shortUrl;

  public ResolveShortUrlResponse() {

  }

  public ResolveShortUrlResponse(String originalUrl, String shortUrl) {
    this.originalUrl = originalUrl;
    this.shortUrl = shortUrl;
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
