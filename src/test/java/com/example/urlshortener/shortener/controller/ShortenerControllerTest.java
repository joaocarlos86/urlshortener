package com.example.urlshortener.shortener.controller;

import com.example.urlshortener.shortener.controller.dto.CreateShortURLRequest;
import com.example.urlshortener.shortener.model.ShortUrl;
import com.example.urlshortener.shortener.service.ShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

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
  void shortenUrl_givenAnythingGoesWrong_shouldReturn500() {
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

  @Test
  void resolveShortUrl_givenShortUrlExists_shouldReturn200() {
    Optional<ShortUrl> shortUrl = Optional.of(new ShortUrl());
    when(service.resolveShortUrl("abc12")).thenReturn(shortUrl);

    testClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/shortener/{token}").build("abc12"))
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  void resolveShortUrl_givenShortUrlDoesntExists_shouldReturn404() {
    Optional<ShortUrl> shortUrl = Optional.empty();
    when(service.resolveShortUrl("abc12")).thenReturn(shortUrl);

    testClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/shortener/{token}").build("abc12"))
        .exchange()
        .expectStatus().isNotFound();
  }

  @Test
  void resolveShortUrl_ifAnythingGoesWrong_shouldReturn500() {
    when(service.resolveShortUrl("abc12")).thenThrow(new RuntimeException());

    testClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/shortener/{token}").build("abc12"))
        .exchange()
        .expectStatus().is5xxServerError();
  }
}