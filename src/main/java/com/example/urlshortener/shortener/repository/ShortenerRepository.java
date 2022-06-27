package com.example.urlshortener.shortener.repository;

import com.example.urlshortener.shortener.model.ShortUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenerRepository extends CrudRepository<ShortUrl, Long> {

  Optional<ShortUrl> findByOriginalUrl(@Param("originalUrl") String originalUrl);
}
