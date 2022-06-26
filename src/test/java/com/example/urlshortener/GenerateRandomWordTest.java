package com.example.urlshortener;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GenerateRandomWordTest {

  GenerateRandomWord generator = new GenerateRandomWord();

  @Test
  void generateWord_shouldGenerateWordWith5Characters() {
    String word = generator.generateWord(5);
    assertThat(word).hasSize(5);
    System.out.println(word);
  }

  @Test
  void generateWord_shouldGenerateWordWithAnyNumberOfCharacters() {
    String word = generator.generateWord(10);
    assertThat(word).hasSize(10);
    System.out.println(word);
  }
}