package com.example.urlshortener.shortener.service;

import com.example.urlshortener.shortener.dto.CreateShortURLRequest;
import com.example.urlshortener.shortener.dto.CreateShortURLResponse;
import com.example.urlshortener.shortener.model.ShortUrl;
import com.example.urlshortener.shortener.repository.ShortenerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ShortenerService {

  private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int DEFAULT_NUMBER_OF_CHARS = 5;
  private ShortenerRepository repository;

  public ShortenerService(ShortenerRepository repository) {
    this.repository = repository;
  }

  public String generateWord() {
    ThreadLocalRandom current = ThreadLocalRandom.current();

    return IntStream.rangeClosed(1, DEFAULT_NUMBER_OF_CHARS)
        .map(i -> current.nextInt(SYMBOLS.length()))
        .mapToObj(i -> String.valueOf(SYMBOLS.charAt(i)))
        .collect(Collectors.joining());
  }

  public ShortUrl createShortUrl(final String urlToShorten) {
    Optional<ShortUrl> byLongUrl = repository.findByOriginalUrl(urlToShorten);

    if (byLongUrl.isPresent()) {
      return byLongUrl.get();
    } else {
      final ShortUrl url = new ShortUrl();
      url.setOriginalUrl(urlToShorten);
      url.setShortToken(generateWord());

      return repository.save(url);
    }


  }

}
