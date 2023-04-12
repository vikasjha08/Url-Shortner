package com.vikas.urlshortner.repository;

import com.vikas.urlshortner.dtos.UrlDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlDao extends CrudRepository<UrlDto, Integer> {

    UrlDto findByShortUrl(String shortUrl);
}
