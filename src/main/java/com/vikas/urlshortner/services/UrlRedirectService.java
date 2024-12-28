package com.vikas.urlshortner.services;

import com.vikas.urlshortner.dtos.InputDto;
import com.vikas.urlshortner.dtos.UrlDto;
import com.vikas.urlshortner.repository.UrlDao;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlRedirectService {

    private final UrlDao dao;

    public UrlDto setRedirectUrl(InputDto dto) {

        return Optional.ofNullable(dao.findByLongUrl(dto.getLongUrl())).orElseGet(() -> dao.save(
                UrlDto.builder()
                        .longUrl(dto.getLongUrl())
                        .shortUrl(createCustomShortUrl())
                        .build()
        ));
    }

    private String createCustomShortUrl() {
        val shortUrl = createRandomShortUrl();
        boolean doesExist = Optional.ofNullable(dao.findByShortUrl(shortUrl)).isPresent();
        if (!doesExist) {
            return shortUrl;
        } else {
            createCustomShortUrl();
        }
        return shortUrl;
    }

    private String createRandomShortUrl() {
        //currently not
        String allowedValuesString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" /*+ "0123456789"*/ + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            int index = (int) (allowedValuesString.length() * Math.random());
            sb.append(allowedValuesString.charAt(index));
        }
        return sb.toString();
    }

    public URI getRedirectUrl(String shortUrlValue) {
        if (!dao.findByShortUrl(shortUrlValue).getLongUrl().contains("https") || !dao.findByShortUrl(shortUrlValue).getLongUrl().contains("http")) {
            return URI.create(String.format("https://%s", dao.findByShortUrl(shortUrlValue).getLongUrl()));
        } else {
            return URI.create(dao.findByShortUrl(shortUrlValue).getLongUrl());
        }
    }

}
