package com.example.urlshortener.shortener.repository;

import com.example.urlshortener.shortener.model.ShortUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepository extends CrudRepository<ShortUrl, Long> {
}
