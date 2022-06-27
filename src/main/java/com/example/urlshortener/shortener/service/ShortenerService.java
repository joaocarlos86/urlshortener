package com.example.urlshortener.shortener.service;

import com.example.urlshortener.shortener.model.ShortUrl;
import com.example.urlshortener.shortener.repository.ShortenerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortenerService {

  private ShortenerRepository repository;
  private RandomWordService randomWordService;

  public ShortenerService(ShortenerRepository repository, RandomWordService randomWordService) {
    this.repository = repository;
    this.randomWordService = randomWordService;
  }

  public ShortUrl createShortUrl(final String urlToShorten) {
    Optional<ShortUrl> byLongUrl = repository.findByOriginalUrl(urlToShorten);

    return byLongUrl.orElseGet(() -> {
          final ShortUrl url = new ShortUrl();
          url.setOriginalUrl(urlToShorten);
          url.setShortToken(randomWordService.generateWord());

          return repository.save(url);
        });
  }
}
