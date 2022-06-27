package com.example.urlshortener.shortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "short_url")
public class ShortUrl {
  @Id
  @GeneratedValue
  private Long id;
  private String originalUrl;
  private String shortToken;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public String getShortToken() {
    return shortToken;
  }

  public void setShortToken(String shortToken) {
    this.shortToken = shortToken;
  }
}
