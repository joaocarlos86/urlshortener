package com.example.urlshortener.shortener.service;

import com.example.urlshortener.shortener.model.ShortUrl;
import com.example.urlshortener.shortener.repository.ShortenerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ShortenerService.class})
class ShortenerServiceTest {
  @Autowired
  private ShortenerService service;
  @MockBean
  private ShortenerRepository repository;
  @MockBean
  private RandomWordService randomWordService;

  @Test
  void createShortUrl_shouldCreateAShortUrlForLongUrl() {
    ShortUrl entity = new ShortUrl();
    entity.setOriginalUrl("www.google.com");
    entity.setToken("abc12");
    when(repository.save(any())).thenReturn(entity);

    ShortUrl shortUrl = service.createShortUrl("www.google.com");
    assertThat(shortUrl.getToken()).isNotEmpty();
    assertThat(shortUrl.getToken()).hasSize(5);
    assertThat(shortUrl.getOriginalUrl()).isEqualTo("www.google.com");
  }

  @Test
  void createShortUrl_ifExistsAShortUrlForLongUrlProvided_shouldReuseShortUrl() {
    ShortUrl shortUrl = new ShortUrl();
    shortUrl.setOriginalUrl("www.google.com");
    shortUrl.setToken("abc13");
    when(repository.findByOriginalUrl("www.google.com")).thenReturn(Optional.of(shortUrl));

    ShortUrl response = service.createShortUrl("www.google.com");
    assertThat(response.getToken()).isNotEmpty();
    assertThat(response.getToken()).isEqualTo("abc13");
    assertThat(response.getOriginalUrl()).isEqualTo("www.google.com");
  }

  @Test
  void createShortUrl_ifExistsAShortUrlForGeneratedShortToken_shouldRetryWithNewShortToken() {
    when(randomWordService.generateWord()).thenReturn("abc12");

    AtomicInteger counter = new AtomicInteger(0);
    when(repository.save(argThat(t -> t.getToken().equals("abc12")))).thenAnswer(methodInvocation -> {
      if (counter.getAndIncrement() < 1) {
        throw new DataIntegrityViolationException("");
      }
;
      return methodInvocation.getArgument(0);
    });

    ShortUrl response = service.createShortUrl("www.google.com");
    assertThat(response).isNotNull();
    assertThat(response.getToken()).isEqualTo("abc12");
    assertThat(response.getOriginalUrl()).isEqualTo("www.google.com");
    assertThat(counter.get()).isEqualTo(2);
  }

  @Test
  void resolveShortUrl_givenTokenExists_shouldReturnShortUrl() {
    when(repository.findByToken("ab123")).thenReturn(Optional.of(new ShortUrl()));

    Optional<ShortUrl> shortUrl = service.resolveShortUrl("ab123");
    assertThat(shortUrl).isPresent();
  }

  @Test
  void resolveShortUrl_givenTokenDoesntExist_shouldReturnEmpty() {
    when(repository.findByToken("ab123")).thenReturn(Optional.empty());

    Optional<ShortUrl> shortUrl = service.resolveShortUrl("ab123");
    assertThat(shortUrl).isNotPresent();
  }

}