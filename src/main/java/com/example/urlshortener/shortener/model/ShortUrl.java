package com.example.urlshortener.shortener.model;

import javax.persistence.*;

@Entity
public class ShortUrl {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String originalUrl;
  @Column(unique = true)
  private String token;

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

  public String getToken() {
    return token;
  }

  public void setToken(String shortToken) {
    this.token = shortToken;
  }
}
