package com.example.urlshortener.shortener.service;

import com.example.urlshortener.shortener.model.ShortUrl;
import com.example.urlshortener.shortener.repository.ShortenerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableRetry
public class ShortenerService {

  private ShortenerRepository repository;
  private RandomWordService randomWordService;

  public ShortenerService(ShortenerRepository repository, RandomWordService randomWordService) {
    this.repository = repository;
    this.randomWordService = randomWordService;
  }

  @Retryable(value = DataIntegrityViolationException.class, maxAttempts = 5, backoff = @Backoff(delay = 10))
  public ShortUrl createShortUrl(final String urlToShorten) {
    Optional<ShortUrl> byLongUrl = repository.findByOriginalUrl(urlToShorten);

    return byLongUrl.orElseGet(() -> {
          final ShortUrl url = new ShortUrl();
          url.setOriginalUrl(urlToShorten);
          url.setToken(randomWordService.generateWord());

          return repository.save(url);
        });
  }

  public Optional<ShortUrl> resolveShortUrl(String token) {
    return repository.findByToken(token);
  }
}
