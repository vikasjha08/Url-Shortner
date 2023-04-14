package com.vikas.urlshortner.repository;

import com.vikas.urlshortner.dtos.UrlDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlDao extends CrudRepository<UrlDto, Integer> {

    UrlDto findByShortUrl(String shortUrl);
    UrlDto findByLongUrl(String longUrl);
}
