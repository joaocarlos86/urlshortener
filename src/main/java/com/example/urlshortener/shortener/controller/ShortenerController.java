package com.example.urlshortener.shortener.controller;

import com.example.urlshortener.shortener.dto.CreateShortURLRequest;
import com.example.urlshortener.shortener.dto.CreateShortURLResponse;
import com.example.urlshortener.shortener.service.ShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("shortener")
public class ShortenerController {

  private ShortenerService service;

  public ShortenerController(ShortenerService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<CreateShortURLResponse> shortenUrl(@Valid @RequestBody CreateShortURLRequest request) {
    CreateShortURLResponse createShortURLResponse = service.createShortUrl(request);
    return ResponseEntity.ok(createShortURLResponse);
  }

}
