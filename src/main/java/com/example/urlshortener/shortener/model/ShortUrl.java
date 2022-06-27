package com.example.urlshortener.shortener.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ShortUrl {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String originalUrl;
  @Column(unique = true)
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
