package com.example.urlshortener.shortener.controller;

import com.example.urlshortener.shortener.dto.CreateShortURLRequest;
import com.example.urlshortener.shortener.model.ShortUrl;
import com.example.urlshortener.shortener.service.ShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = ShortenerController.class)
class ShortenerControllerTest {

  @Autowired
  private WebTestClient testClient;
  @MockBean
  private ShortenerService service;

  @Test
  void shortenUrl_givenValidRequest_shouldReturn200() {
    CreateShortURLRequest request = new CreateShortURLRequest("http://www.google.com");

    ShortUrl shortUrl = new ShortUrl();
    when(service.createShortUrl(any())).thenReturn(shortUrl);

    testClient
        .post()
        .uri("/shortener")
        .body(Mono.just(request), CreateShortURLRequest.class)
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void shortenUrl_givenNoLongUrlProvided_shouldReturn400() {
    CreateShortURLRequest request = new CreateShortURLRequest();

    testClient
        .post()
        .uri("/shortener")
        .body(Mono.just(request), CreateShortURLRequest.class)
        .exchange()
        .expectStatus()
        .is4xxClientError()
        .expectBody()
        .jsonPath("$.url").isEqualTo("must not be blank");
  }

  @Test
  void shortenUrl_givenAnythingGoWrong_shouldReturn500() {
    CreateShortURLRequest request = new CreateShortURLRequest("www.google.com");

    when(service.createShortUrl(any())).thenThrow(new RuntimeException());

    testClient
        .post()
        .uri("/shortener")
        .body(Mono.just(request), CreateShortURLRequest.class)
        .exchange()
        .expectStatus()
        .is5xxServerError();
  }
}