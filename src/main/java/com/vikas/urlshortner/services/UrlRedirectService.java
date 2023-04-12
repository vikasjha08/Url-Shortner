package com.vikas.urlshortner.services;

import com.vikas.urlshortner.dtos.UrlDto;
import com.vikas.urlshortner.repository.UrlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UrlRedirectService {

    private final UrlDao dao;

    public UrlDto setRedirectUrl(UrlDto dto) {
        return dao.save(dto);
    }

    public URI getRedirectUrl(String shortUrlValue) {
        if (!dao.findByShortUrl(shortUrlValue).getLongUrl().contains("https") || !dao.findByShortUrl(shortUrlValue).getLongUrl().contains("http")) {
            return URI.create(String.format("https://%s", dao.findByShortUrl(shortUrlValue).getLongUrl()));
        } else {
            return URI.create(dao.findByShortUrl(shortUrlValue).getLongUrl());
        }
    }

}
