package com.example.urlshortener.shortener.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RandomWordService {
  private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int DEFAULT_NUMBER_OF_CHARS = 5;

  public String generateWord() {
    ThreadLocalRandom current = ThreadLocalRandom.current();

    return IntStream.rangeClosed(1, DEFAULT_NUMBER_OF_CHARS)
        .map(i -> current.nextInt(SYMBOLS.length()))
        .mapToObj(i -> String.valueOf(SYMBOLS.charAt(i)))
        .collect(Collectors.joining());
  }

  public String generateUniqueWord() {
    return generateWord();
  }
}
