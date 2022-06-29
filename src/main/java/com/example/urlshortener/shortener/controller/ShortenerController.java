package com.example.urlshortener.shortener.controller;

import com.example.urlshortener.shortener.controller.dto.CreateShortURLRequest;
import com.example.urlshortener.shortener.controller.dto.CreateShortURLResponse;
import com.example.urlshortener.shortener.controller.dto.ResolveShortURLResponse;
import com.example.urlshortener.shortener.model.ShortUrl;
import com.example.urlshortener.shortener.service.ShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ShortUrl shortUrl = service.createShortUrl(request.getUrl());
    CreateShortURLResponse createShortURLResponse = new CreateShortURLResponse(shortUrl.getOriginalUrl(), shortUrl.getToken());
    return ResponseEntity.ok(createShortURLResponse);
  }

  @GetMapping("/{token}")
  public ResponseEntity<ResolveShortURLResponse> resolveShortUrl(@PathVariable("token") final String token) {
    return service.resolveShortUrl(token)
        .map(shortUrl -> ResponseEntity.ok(new ResolveShortURLResponse(shortUrl.getOriginalUrl(), shortUrl.getToken())))
        .orElse(ResponseEntity.notFound().build());
  }

}
