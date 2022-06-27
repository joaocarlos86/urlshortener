package com.example.urlshortener.shortener.controller;

import com.example.urlshortener.shortener.dto.CreateShortURLResponse;
import com.example.urlshortener.shortener.dto.CreateShortURLRequest;
import com.example.urlshortener.shortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shortener")
public class ShortenerController {

  @PostMapping
  public CreateShortURLResponse shortenUrl(@RequestBody CreateShortURLRequest request) {
    CreateShortURLResponse createShortURLResponse = new CreateShortURLResponse(request.getUrl());
    createShortURLResponse.setShortUrl("OK123");
    return createShortURLResponse;
  }

}
