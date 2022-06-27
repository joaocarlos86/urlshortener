package com.example.urlshortener.shortener.repository;

import com.example.urlshortener.shortener.model.ShortUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ShortenerRepositoryTest {

  @Autowired
  private ShortenerRepository repository;

  @Test
  void findByShortToken_givenShortTokenExists_shouldReturnShortUrl() {
    ShortUrl url1 = new ShortUrl();
    url1.setShortToken("ok123");
    url1.setOriginalUrl("www.google.com");
    repository.save(url1);

    Optional<ShortUrl> url = repository.findByShortToken("ok123");
    assertThat(url).isNotNull();
    assertThat(url.isPresent()).isTrue();
    assertThat(url.get().getId()).isNotNull();
    assertThat(url.get().getShortToken()).isEqualTo("ok123");
    assertThat(url.get().getOriginalUrl()).isEqualTo("www.google.com");
  }

  @Test
  void findByShortToken_givenShortTokenDoesNotExists_shouldReturnEmptyOptional() {
    Optional<ShortUrl> url = repository.findByShortToken("ok123");
    assertThat(url).isNotNull();
    assertThat(url.isPresent()).isFalse();
  }

  @Test
  void findByOriginalUrl_givenOriginalUrlExists_shouldReturnShortUrl() {
    ShortUrl url1 = new ShortUrl();
    url1.setShortToken("ok123");
    url1.setOriginalUrl("www.google.com");
    repository.save(url1);

    Optional<ShortUrl> url = repository.findByOriginalUrl("www.google.com");
    assertThat(url).isNotNull();
    assertThat(url.isPresent()).isTrue();
    assertThat(url.get().getId()).isNotNull();
    assertThat(url.get().getShortToken()).isEqualTo("ok123");
    assertThat(url.get().getOriginalUrl()).isEqualTo("www.google.com");
  }

  @Test
  void findByOriginalUrl_givenOriginalUrlDoesNotExists_shouldReturnEmptyOptional() {
    Optional<ShortUrl> url = repository.findByOriginalUrl("www.google.com");
    assertThat(url).isNotNull();
    assertThat(url.isPresent()).isFalse();
  }

  @Test
  void givenShortTokenAlreadyExists_shouldThrowDataIntegrityException() {
    ShortUrl url1 = new ShortUrl();
    url1.setShortToken("ok123");
    url1.setOriginalUrl("www.google.com");
    repository.save(url1);

    ShortUrl url2 = new ShortUrl();
    url2.setShortToken("ok123");
    url2.setOriginalUrl("www2.google.com");
    assertThatThrownBy(() -> repository.save(url2)).isInstanceOf(DataIntegrityViolationException.class);
  }

  @Test
  void givenOriginalUrlAlreadyExists_shouldThrowDataIntegrityException() {
    ShortUrl url1 = new ShortUrl();
    url1.setShortToken("ok123");
    url1.setOriginalUrl("www.google.com");
    repository.save(url1);

    ShortUrl url2 = new ShortUrl();
    url2.setShortToken("ok124");
    url2.setOriginalUrl("www.google.com");
    assertThatThrownBy(() -> repository.save(url2)).isInstanceOf(DataIntegrityViolationException.class);
  }

}