package com.example.urlshortener.shortener.service;

import com.example.urlshortener.shortener.dto.CreateShortURLRequest;
import com.example.urlshortener.shortener.dto.CreateShortURLResponse;
import com.example.urlshortener.shortener.repository.ShortenerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {ShortenerService.class})
class ShortenerServiceTest {
  @Autowired
  private ShortenerService service;
  @MockBean
  private ShortenerRepository repository;

  @Test
  void generateWord_shouldGenerateWordWith5Characters() {
    String word = service.generateWord();
    assertThat(word).hasSize(5);
  }

  @Test
  void createShortUrl_shouldCreateAShortUrlForLongUrl() {
    CreateShortURLRequest request = new CreateShortURLRequest();
    request.setUrl("www.google.com");
    CreateShortURLResponse shortUrl = service.createShortUrl(request);
    assertThat(shortUrl.getShortUrl()).isNotEmpty();
    assertThat(shortUrl.getShortUrl()).hasSize(5);
    assertThat(shortUrl.getOriginalUrl()).isEqualTo("www.google.com");
  }
}