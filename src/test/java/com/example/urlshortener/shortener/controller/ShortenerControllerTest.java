package com.example.urlshortener.shortener.controller;

import com.example.urlshortener.shortener.dto.CreateShortURLRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = ShortenerController.class)
class ShortenerControllerTest {

  @Autowired
  private WebTestClient testClient;

  @Test
  void shortenUrl_givenValidRequest_shouldReturnShortenedVersionOfUrl() {
    CreateShortURLRequest request = new CreateShortURLRequest("http://www.google.com");

    testClient
        .post()
        .uri("/shortener")
        .body(Mono.just(request), CreateShortURLRequest.class)
        .exchange()
        .expectStatus()
        .isOk();


  }
}