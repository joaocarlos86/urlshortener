package com.example.urlshortener.shortener.service;

import com.example.urlshortener.shortener.model.ShortUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RandomWordService.class})
class RandomWordServiceTest {

  @Autowired
  private RandomWordService service;

  @Test
  void generateWord_shouldGenerateWordWith5Characters() {
    String word = service.generateWord();
    assertThat(word).hasSize(5);
  }


}