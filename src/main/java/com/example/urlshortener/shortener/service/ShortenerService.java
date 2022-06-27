package com.example.urlshortener.shortener.service;

import com.example.urlshortener.shortener.dto.CreateShortURLRequest;
import com.example.urlshortener.shortener.dto.CreateShortURLResponse;
import org.springframework.stereotype.Service;

@Service
public class ShortenerService {

  public CreateShortURLResponse createShortUrl(CreateShortURLRequest request) {
    CreateShortURLResponse createShortURLResponse = new CreateShortURLResponse(request.getUrl());
    createShortURLResponse.setShortUrl("OK123");
    return createShortURLResponse;
  }

}
