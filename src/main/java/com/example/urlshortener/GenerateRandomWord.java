package com.example.urlshortener;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateRandomWord {
  public static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

  public String generateWord(Integer numberOfCharacters) {
    ThreadLocalRandom current = ThreadLocalRandom.current();

    return IntStream.rangeClosed(1, numberOfCharacters)
        .map(i -> current.nextInt(SYMBOLS.length()))
        .mapToObj(i -> String.valueOf(SYMBOLS.charAt(i)))
        .collect(Collectors.joining());
  }
}
