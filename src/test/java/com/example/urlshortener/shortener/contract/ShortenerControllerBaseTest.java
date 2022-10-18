package com.example.urlshortener.shortener.contract;

import com.example.urlshortener.shortener.controller.ShortenerController;
import com.example.urlshortener.shortener.model.ShortUrl;
import com.example.urlshortener.shortener.service.ShortenerService;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = ShortenerController.class)
public class ShortenerControllerBaseTest {

  @MockBean
  private ShortenerService service;
  @Autowired
  private ShortenerController controller;

  @BeforeEach
  public void setup() {
    ShortUrl url = new ShortUrl();
    url.setOriginalUrl("www.google.com");
    url.setToken("123");
    url.setId(1L);

    Optional<ShortUrl> shortUrl = Optional.of(url);
    when(service.resolveShortUrl("123")).thenReturn(shortUrl);

    RestAssuredWebTestClient.standaloneSetup(controller);
  }
}
