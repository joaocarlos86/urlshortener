package com.example.urlshortener.shortener;

import com.example.urlshortener.shortener.dto.CreateShortURLRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class IntegrationTest {
  @Autowired
  private WebTestClient webClient;

  @Test
  void canCreateShorUrlFromLongUrl() {
    CreateShortURLRequest createShortUrl = new CreateShortURLRequest("www.google.com");
    webClient
        .post()
        .uri("/shortener")
        .body(Mono.just(createShortUrl), CreateShortURLRequest.class)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.originalUrl").isEqualTo("www.google.com")
        .jsonPath("$.shortUrl").isNotEmpty()
        .jsonPath("$.shortUrl").value(o -> {
          assertThat(o.toString()).hasSize(5);
        });
  }
}
